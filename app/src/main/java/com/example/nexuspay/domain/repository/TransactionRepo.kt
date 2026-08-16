package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.utils.exception.TransactionResult

interface TransactionRepo {
    suspend fun getAllTransactionRepo() : Result<List<TransactionResponse>>
    suspend fun getAllUsersRepo() :  Result<List<CurrentUserItem>>
    suspend fun sendRequestRepo(entity: TransactionEntity) : TransactionResult
    suspend fun saveRequestRepo(entity: TransactionEntity) : TransactionResult
    suspend fun retrySendMoney(id : Int) : TransactionResult
}