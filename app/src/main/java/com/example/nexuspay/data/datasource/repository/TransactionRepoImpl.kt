package com.example.nexuspay.data.datasource.repository

import com.example.nexuspay.data.datasource.local_ds.transaction.TransactionLocalData
import com.example.nexuspay.data.datasource.remote_ds.transaction.TransactionRemoteData
import com.example.nexuspay.data.setup.connectivity.Connectivity
import com.example.nexuspay.data.setup.toRequest
import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.domain.repository.TransactionRepo
import com.example.nexuspay.utils.exception.TransactionResult
import com.example.nexuspay.utils.exception.checkException
import com.example.nexuspay.workmanager.ScheduleManager

class TransactionRepoImpl(
    private val transactionRemoteData: TransactionRemoteData,
    private val transactionLocalData: TransactionLocalData,
    private val scheduleManager: ScheduleManager,
    private val connect : Connectivity,
) : TransactionRepo {

    override suspend fun transactionRepoData() : Result<List<TransactionResponse>> {
        if (connect.isOnline()){
            val result = transactionRemoteData.transactionData()
            if(result.isSuccess && result.getOrNull() != null){
                val response = result.getOrNull()!!
                transactionLocalData.saveListOfTransaction(response)
                return Result.success(response)
            } else {
                return Result.failure(result.exceptionOrNull()!!)
            }
        } else {
            val data = transactionLocalData.getListOfTransaction()
            return Result.success(data)
        }
    }

    override suspend fun currentTransactionRepo(): Result<List<CurrentUserItem>> {
        val result = transactionRemoteData.recentUserTransactionData()
        if(result.isSuccess && result.getOrNull() != null){
            val response = result.getOrNull()!!
            return Result.success(response)
        } else {
            return Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun createTransactionRepo(entity: TransactionEntity): TransactionResult {

        val savedEntity =
            transactionLocalData.addRequestTransaction(entity)

        val result =
            requestTransactionRepo(savedEntity)

        if (result == TransactionResult.Pending) {
            scheduleManager.scheduleRetry(savedEntity.id!!)
        }

        return result
    }

    override suspend fun requestTransactionRepo(entity : TransactionEntity) : TransactionResult {

        val result = transactionRemoteData.requestTransaction(entity.toRequest())


        if (result.isSuccess && result.getOrNull() != null) {
            transactionLocalData.deleteRequestTransaction(entity)
            return TransactionResult.Success
        }

        val exception = result.exceptionOrNull()

        if (exception != null && checkException(exception)) {
            return TransactionResult.Pending
        } else {
            transactionLocalData.deleteRequestTransaction(entity)
            return TransactionResult.Failed
        }
    }

    override suspend fun retryTransaction(id: Int) : TransactionResult {
        val entity =  transactionLocalData.getRequestTransaction(id)
        return  requestTransactionRepo(entity)
    }
}