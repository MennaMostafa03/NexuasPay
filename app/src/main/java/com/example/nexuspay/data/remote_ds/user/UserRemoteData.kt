package com.example.nexuspay.data.remote_ds.user

import com.example.nexuspay.domain.model.response.UserResponse


interface UserRemoteData {
    suspend fun getUser(): Result<UserResponse>
}