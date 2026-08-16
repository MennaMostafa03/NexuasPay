package com.example.nexuspay.data.datasource.remote_ds.transaction

import com.example.nexuspay.domain.model.request.TransactionRequest
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse

interface TransactionRemoteData {
    suspend fun transactionData(): Result<List<TransactionResponse>>
    suspend fun recentUserTransactionData(): Result<List<CurrentUserItem>>
    suspend fun requestTransaction(request: TransactionRequest): Result<Unit>
}