package com.example.nexuspay.domain.usecase.transaction

import com.example.nexuspay.domain.repository.TransactionRepo


class GetTransactionUseCase(private val remoteDataRepo: TransactionRepo) {
    suspend fun invoke() = remoteDataRepo.getAllTransactionRepo()
}