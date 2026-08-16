package com.example.nexuspay.data.datasource.local_ds.user

import com.example.nexuspay.domain.model.response.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserLocalData {

    suspend fun saveUser(user : UserResponse)

    fun getUser() : Flow<UserResponse>
}