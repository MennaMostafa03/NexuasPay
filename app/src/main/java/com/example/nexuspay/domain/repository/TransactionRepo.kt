package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.utils.exception.TransactionResult

interface TransactionRepo {
    suspend fun transactionRepoData() : Result<List<TransactionResponse>>
    suspend fun currentTransactionRepo() :  Result<List<CurrentUserItem>>
    suspend fun requestTransactionRepo(entity: TransactionEntity) : TransactionResult
    suspend fun createTransactionRepo(entity: TransactionEntity) : TransactionResult
    suspend fun retryTransaction(id : Int) : TransactionResult
}