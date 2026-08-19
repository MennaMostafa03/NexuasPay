package com.example.nexuspay.domain.usecase.transaction

import com.example.nexuspay.domain.repository.TransactionRepo

class GetAllUserUseCase(private val remoteDataRepo: TransactionRepo) {
    suspend fun invoke()  = remoteDataRepo.getAllUsersRepo()
}