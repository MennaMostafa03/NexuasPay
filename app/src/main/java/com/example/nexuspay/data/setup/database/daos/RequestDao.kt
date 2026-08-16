package com.example.nexuspay.data.setup.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nexuspay.domain.model.request.TransactionEntity

@Dao
interface RequestDao {

    @Insert
    suspend fun saveRequest(entity : TransactionEntity) : Long

    @Delete
    suspend fun deleteRequest(entity: TransactionEntity)

    @Query("Select * From TransactionEntity where id == :transactionId")
    suspend fun getRequestById(transactionId: Int) : TransactionEntity

}