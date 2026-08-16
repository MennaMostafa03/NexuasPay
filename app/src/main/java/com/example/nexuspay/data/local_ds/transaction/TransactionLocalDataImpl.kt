package com.example.nexuspay.data.local_ds.transaction

import android.util.Log
import com.example.nexuspay.data.setup.database.daos.RequestDao
import com.example.nexuspay.data.setup.database.daos.TransactionDao
import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.response.TransactionResponse

class TransactionLocalDataImpl(
    val transactionDao: TransactionDao,
    val requestTransactionDao: RequestDao
) : TransactionLocalData {

    override suspend fun saveTransactionInDB(transaction: List<TransactionResponse>) {
        transactionDao.saveTransaction(transaction)
    }

    override suspend fun getTransactionFromDB(): List<TransactionResponse> {
        return transactionDao.getAllTransaction()
    }

    override suspend fun saveRequestInDB(transaction: RequestEntity) : RequestEntity{
        val cleanTransaction = transaction.copy(id = null)
        val newId = requestTransactionDao.saveRequest(cleanTransaction)
        return cleanTransaction.copy(id = newId.toInt())
    }

    override suspend fun deleteRequestFromDB(entity: RequestEntity) {
        requestTransactionDao.deleteRequest(entity)
    }

    override suspend fun getRequestByIdFromDB(transactionId: Int) : RequestEntity{
        return requestTransactionDao.getRequestById(transactionId)
    }

}