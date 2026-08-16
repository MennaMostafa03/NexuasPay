package com.example.nexuspay.data.datasource.local_ds.transaction

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.TransactionResponse

interface TransactionLocalData {
    suspend fun getListOfTransaction() : List<TransactionResponse>
    suspend fun saveListOfTransaction(transaction : List<TransactionResponse>)

    suspend fun addRequestTransaction(transaction : TransactionEntity) : TransactionEntity
    suspend fun deleteRequestTransaction(entity: TransactionEntity)

    suspend fun getRequestTransaction(transactionId: Int): TransactionEntity
//    suspend fun getAllRequestTransaction(): List<TransactionEntity>
}