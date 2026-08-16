package com.example.nexuspay.data.setup.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nexuspay.domain.model.request.TransactionEntity

@Dao
interface RequestTransactionDao {

    @Insert
    suspend fun addTransactionRequest(entity : TransactionEntity) : Long

    @Delete
    suspend fun deleteTransactionRequest(entity: TransactionEntity)

    @Query("Select * From TransactionEntity where id == :transactionId")
    suspend fun getTransactionRequest(transactionId: Int) : TransactionEntity

    @Query("Select * From TransactionEntity")
    suspend fun getAllTransactionRequest() : List<TransactionEntity>
}