package com.example.nexuspay.domain.usecase

import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.repository.TransactionRepo

class SaveRequestUseCase(
    private val transactionRepo: TransactionRepo
) {
    suspend fun invoke(entity: RequestEntity) = transactionRepo.saveRequestRepo(entity)
}