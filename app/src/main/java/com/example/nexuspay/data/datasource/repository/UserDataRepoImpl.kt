package com.example.nexuspay.data.datasource.repository

import com.example.nexuspay.data.datasource.local_ds.user.UserLocalData
import com.example.nexuspay.data.datasource.remote_ds.user.UserRemoteData
import com.example.nexuspay.data.setup.connectivity.Connectivity
import com.example.nexuspay.domain.model.response.UserResponse
import com.example.nexuspay.domain.repository.UserDataRepo
import kotlinx.coroutines.flow.first

class UserDataRepoImpl(
    private val userRemoteData: UserRemoteData,
    private val userLocalData: UserLocalData,
    private val connect : Connectivity,
) : UserDataRepo {

    override suspend fun userRepoData() : Result<UserResponse> {
        if (connect.isOnline()){
            val result = userRemoteData.userData()
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