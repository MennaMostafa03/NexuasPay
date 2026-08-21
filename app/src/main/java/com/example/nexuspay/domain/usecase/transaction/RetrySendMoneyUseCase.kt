package com.example.nexuspay.domain.usecase.transaction

import com.example.nexuspay.domain.repository.TransactionRepo

class RetrySendMoneyUseCase  (
    private val transactionRepo: TransactionRepo
) {
    suspend fun invoke() = transactionRepo.retrySendMoney()
}