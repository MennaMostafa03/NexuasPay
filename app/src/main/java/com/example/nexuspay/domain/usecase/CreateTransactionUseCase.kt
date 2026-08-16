package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.repository.TransactionRepo

class CreateTransactionUseCase(
    private val transactionRepo: TransactionRepo
) {
    suspend fun invoke(entity: TransactionEntity) = transactionRepo.createTransactionRepo(entity)
}