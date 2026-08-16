package com.example.nexuspay.data.remote_ds.transaction

import com.example.nexuspay.domain.model.request.TransactionRequest
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse

interface TransactionRemoteData {
    suspend fun getAllTransaction(): Result<List<TransactionResponse>>
    suspend fun getAllUsers(): Result<List<CurrentUserItem>>
    suspend fun sendRequest(request: TransactionRequest): Result<Unit>
}