package com.example.nexuspay.data.repository

import com.example.nexuspay.data.local_ds.user.UserLocalData
import com.example.nexuspay.data.remote_ds.user.UserRemoteData
import com.example.nexuspay.data.setup.connectivity.Connectivity
import com.example.nexuspay.domain.model.response.UserResponse
import com.example.nexuspay.domain.repository.UserRepo
import kotlinx.coroutines.flow.first

class UserRepoImpl(
    private val userRemoteData: UserRemoteData,
    private val userLocalData: UserLocalData,
    private val connect : Connectivity,
) : UserRepo {

    override suspend fun getUserRepo() : Result<UserResponse> {
        if (connect.isOnline()){
            val result = userRemoteData.getUser()
            if(result.isSuccess && result.getOrNull() != null){
                val response = result.getOrNull()!!
                userLocalData.saveUser(response)
                return Result.success(response)
            } else {
                return Result.failure(result.exceptionOrNull()!!)
            }
        } else {
            val data = userLocalData.getUser().first()
            return Result.success(data)
        }
    }
}