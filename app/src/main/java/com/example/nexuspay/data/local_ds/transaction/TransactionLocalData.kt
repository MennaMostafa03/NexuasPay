package com.example.nexuspay.data.local_ds.transaction

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.TransactionResponse

interface TransactionLocalData {

    suspend fun saveTransactionInDB(transaction : List<TransactionResponse>)
    suspend fun getTransactionFromDB() : List<TransactionResponse>
    suspend fun saveRequestInDB(transaction : TransactionEntity) : TransactionEntity
    suspend fun deleteRequestFromDB(entity: TransactionEntity)
    suspend fun getRequestByIdFromDB(transactionId: Int): TransactionEntity
}