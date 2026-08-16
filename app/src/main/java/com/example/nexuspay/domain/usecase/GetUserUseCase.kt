package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.repository.UserRepo

class GetUserUseCase(private val remoteDataRepo: UserRepo) {
    suspend fun invoke()  = remoteDataRepo.getUserRepo()
}