package com.example.nexuspay.data.setup.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexuspay.data.setup.database.daos.RequestTransactionDao
import com.example.nexuspay.data.setup.database.daos.TransactionDao
import com.example.nexuspay.domain.model.request.TransactionEntity


@Database(entities = [TransactionEntity::class], version = 1)
abstract class TransactionRequestDataBase : RoomDatabase() {
    abstract fun getRequestTransactionDao() : RequestTransactionDao
}