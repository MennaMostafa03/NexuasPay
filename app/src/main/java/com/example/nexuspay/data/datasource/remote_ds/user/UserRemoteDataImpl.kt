package com.example.nexuspay.data.datasource.remote_ds.user


import com.example.nexuspay.data.setup.api.USER_IDENTIFIER
import com.example.nexuspay.domain.model.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class UserRemoteDataImpl(private val httpClient : HttpClient) : UserRemoteData {

    override suspend fun userData() : Result<UserResponse>{
        try {
            val response = httpClient.get("users/${USER_IDENTIFIER}")
            val userResponse = response.body<UserResponse>()
            return Result.success(userResponse)
       } catch (e : Throwable) {
            return Result.failure(e)
       }
    }

}