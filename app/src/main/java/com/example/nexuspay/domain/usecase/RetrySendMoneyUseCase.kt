package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.repository.TransactionRepo

class RetrySendMoneyUseCase  (
    private val transactionRepo: TransactionRepo
) {
    suspend fun invoke(id: Int) = transactionRepo.retrySendMoney(id)
}