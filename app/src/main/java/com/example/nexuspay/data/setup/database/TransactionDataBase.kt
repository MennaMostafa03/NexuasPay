package com.example.nexuspay.data.setup.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexuspay.data.setup.database.daos.TransactionDao
import com.example.nexuspay.domain.model.response.TransactionResponse

@Database(entities = [TransactionResponse::class], version = 1)
abstract class TransactionDataBase : RoomDatabase() {
    abstract fun getTransactionDao() : TransactionDao
}