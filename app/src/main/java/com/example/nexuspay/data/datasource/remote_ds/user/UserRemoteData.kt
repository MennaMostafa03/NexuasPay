package com.example.nexuspay.data.datasource.remote_ds.user

import com.example.nexuspay.domain.model.response.UserResponse


interface UserRemoteData {
    suspend fun userData(): Result<UserResponse>
}