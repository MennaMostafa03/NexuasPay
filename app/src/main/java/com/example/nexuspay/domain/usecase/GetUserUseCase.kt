package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.repository.UserDataRepo

class GetUserUseCase(private val remoteDataRepo: UserDataRepo) {
    suspend fun invoke()  = remoteDataRepo.userRepoData()
}