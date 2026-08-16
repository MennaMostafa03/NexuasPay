package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.response.UserResponse


interface UserRepo {
    suspend fun getUserRepo() :  Result<UserResponse>


}