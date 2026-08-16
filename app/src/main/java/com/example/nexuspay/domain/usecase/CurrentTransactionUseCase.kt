package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.repository.TransactionRepo

class CurrentTransactionUseCase(private val remoteDataRepo: TransactionRepo) {
    suspend fun invoke()  = remoteDataRepo.currentTransactionRepo()
}