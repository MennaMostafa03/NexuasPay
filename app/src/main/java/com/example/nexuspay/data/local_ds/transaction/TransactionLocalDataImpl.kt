package com.example.nexuspay.data.local_ds.transaction

import android.util.Log
import com.example.nexuspay.data.setup.database.daos.RequestDao
import com.example.nexuspay.data.setup.database.daos.TransactionDao
import com.example.nexuspay.domain.model.request.TransactionEntity
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

    override suspend fun saveRequestInDB(transaction: TransactionEntity) : TransactionEntity{
        val cleanTransaction = transaction.copy(id = null)
        Log.d("RoomDebug", "Trying to insert: $cleanTransaction")

        val newId = requestTransactionDao.saveRequest(cleanTransaction)
        Log.d("RoomDebug", "Inserted with id: $newId")

        return cleanTransaction.copy(id = newId.toInt())
    }

    override suspend fun deleteRequestFromDB(entity: TransactionEntity) {
        requestTransactionDao.deleteRequest(entity)
    }

    override suspend fun getRequestByIdFromDB(transactionId: Int) : TransactionEntity{
        return requestTransactionDao.getRequestById(transactionId)
    }

}