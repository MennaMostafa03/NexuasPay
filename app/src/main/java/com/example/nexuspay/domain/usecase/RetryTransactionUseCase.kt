package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.repository.TransactionRepo

class RetryTransactionUseCase  (
    private val transactionRepo: TransactionRepo
) {
    suspend fun invoke(id: Int) = transactionRepo.retryTransaction(id)
}