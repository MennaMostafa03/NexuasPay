package com.example.nexuspay.data.local_ds.transaction

import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.response.TransactionResponse

interface TransactionLocalData {

    suspend fun saveTransactionInDB(transaction : List<TransactionResponse>)
    suspend fun getTransactionFromDB() : List<TransactionResponse>
    suspend fun saveRequestInDB(transaction : RequestEntity) : RequestEntity
    suspend fun deleteRequestFromDB(entity: RequestEntity)
    suspend fun getRequestByIdFromDB(transactionId: Int): RequestEntity
}