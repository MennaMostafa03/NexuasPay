package com.example.nexuspay.data.setup.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexuspay.domain.model.response.TransactionResponse

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTransaction(transaction : List<TransactionResponse>)

    @Query("select * from  TransactionResponse ")
    suspend fun getAllTransaction() : List<TransactionResponse>
}


