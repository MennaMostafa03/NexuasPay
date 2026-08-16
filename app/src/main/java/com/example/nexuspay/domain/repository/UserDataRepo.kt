package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.response.UserResponse


interface UserDataRepo {
    suspend fun userRepoData() :  Result<UserResponse>


}