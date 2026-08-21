package com.example.nexuspay.data.repository

import com.example.nexuspay.data.local_ds.transaction.TransactionLocalData
import com.example.nexuspay.data.remote_ds.transaction.TransactionRemoteData
import com.example.nexuspay.data.setup.connectivity.Connectivity
import com.example.nexuspay.data.setup.toRequest
import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.domain.repository.TransactionRepo
import com.example.nexuspay.utils.exception.TransactionResult
import com.example.nexuspay.utils.exception.checkException
import com.example.nexuspay.workmanager.ScheduleManager

class TransactionRepoImpl(
    private val remoteDB: TransactionRemoteData,
    private val localDB: TransactionLocalData,
    private val scheduleManager: ScheduleManager,
    private val connect : Connectivity,
) : TransactionRepo {

    override suspend fun getAllTransactionRepo() : Result<List<TransactionResponse>> {
        if (connect.isOnline()){
            // get transaction from api
            val result = remoteDB.getAllTransaction()
            if(result.isSuccess && result.getOrNull() != null){
                val response = result.getOrNull()!!
                // save transaction in DB
                localDB.saveTransactionInDB(response)
                return Result.success(response)
            } else {
                return Result.failure(result.exceptionOrNull()!!)
            }
        } else {
            // get transaction from DB
            val data = localDB.getTransactionFromDB()
            return Result.success(data)
        }
    }

    override suspend fun getAllUsersRepo(): Result<List<CurrentUserItem>> {
        val result = remoteDB.getAllUsers()
        if(result.isSuccess && result.getOrNull() != null){
            val response = result.getOrNull()!!
            return Result.success(response)
        } else {
            return Result.failure(result.exceptionOrNull()!!)
        }
    }

    override suspend fun saveRequestRepo(entity: RequestEntity): TransactionResult {
        val savedEntity = localDB.saveRequestInDB(entity)
        val result = sendRequestRepo(savedEntity)
        if (result == TransactionResult.Pending) {
            scheduleManager.scheduleRetry()
        }
        return result
    }
    override suspend fun sendRequestRepo(entity : RequestEntity) : TransactionResult {
        val result = remoteDB.sendRequest(entity.toRequest())
        if (result.isSuccess && result.getOrNull() != null) {
            localDB.deleteRequestFromDB(entity)
            return TransactionResult.Success
        }
        val exception = result.exceptionOrNull()
        if (exception != null && checkException(exception)) {
            return TransactionResult.Pending
        } else {
            localDB.deleteRequestFromDB(entity)
            return TransactionResult.Failed
        }
    }

    override suspend fun retrySendMoney()  {
        val pendingTransaction =  localDB.getRequestFromDB()
        pendingTransaction.forEach { transaction ->
            sendRequestRepo(transaction)
        }
    }
}