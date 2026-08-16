package com.example.nexuspay.data.remote_ds.transaction

import com.example.nexuspay.data.setup.api.USER_IDENTIFIER
import com.example.nexuspay.domain.model.request.TransactionRequest
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class TransactionRemoteDataImpl(private val httpClient : HttpClient) : TransactionRemoteData {

    override suspend fun getAllTransaction() : Result<List<TransactionResponse>>{
        try {
            val response = httpClient.get("transactions/${USER_IDENTIFIER}")
            val transactionResponse = response.body<List<TransactionResponse>>()
            return Result.success(transactionResponse)
        } catch (e : Throwable) {
            return Result.failure(e)
        }
    }

    override suspend fun getAllUsers(): Result<List<CurrentUserItem>> {
        try {
            val response = httpClient.get("users")
            val recentUserResponse = response.body<List<CurrentUserItem>>()
            return Result.success(recentUserResponse)
        } catch (e : Throwable) {
            return Result.failure(e)
        }
    }

    override suspend fun sendRequest(request: TransactionRequest): Result<Unit> {
        return try {

            val response = httpClient.post("transactions/send") {
                setBody(request)
            }

            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(
                    Exception("HTTP ${response.status.value}")
                )
            }

        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}