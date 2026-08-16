package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.utils.exception.TransactionResult

interface TransactionRepo {
    suspend fun getAllTransactionRepo() : Result<List<TransactionResponse>>
    suspend fun getAllUsersRepo() :  Result<List<CurrentUserItem>>
    suspend fun sendRequestRepo(entity: RequestEntity) : TransactionResult
    suspend fun saveRequestRepo(entity: RequestEntity) : TransactionResult
    suspend fun retrySendMoney(id : Int) : TransactionResult
}