package com.example.nexuspay.data.datasource.local_ds.transaction

import android.util.Log
import com.example.nexuspay.data.setup.database.daos.RequestTransactionDao
import com.example.nexuspay.data.setup.database.daos.TransactionDao
import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.TransactionResponse

class TransactionLocalDataImpl(
    val transactionDao: TransactionDao,
    val requestTransactionDao: RequestTransactionDao
) : TransactionLocalData {

    override suspend fun saveListOfTransaction(transaction: List<TransactionResponse>) {
        transactionDao.addListTransaction(transaction)
    }

    override suspend fun getListOfTransaction(): List<TransactionResponse> {
        return transactionDao.getAllTransaction()
    }

    override suspend fun addRequestTransaction(transaction: TransactionEntity) : TransactionEntity{
        val cleanTransaction = transaction.copy(id = null)
        Log.d("RoomDebug", "Trying to insert: $cleanTransaction")

        val newId = requestTransactionDao.addTransactionRequest(cleanTransaction)
        Log.d("RoomDebug", "Inserted with id: $newId")

        return cleanTransaction.copy(id = newId.toInt())
    }

    override suspend fun deleteRequestTransaction(entity: TransactionEntity) {
        requestTransactionDao.deleteTransactionRequest(entity)
    }

    override suspend fun getRequestTransaction(transactionId: Int) : TransactionEntity{
        return requestTransactionDao.getTransactionRequest(transactionId)
    }

//    override suspend fun getAllRequestTransaction() : List<TransactionEntity>{
//        return requestTransactionDao.getAllTransactionRequest()
//    }

}