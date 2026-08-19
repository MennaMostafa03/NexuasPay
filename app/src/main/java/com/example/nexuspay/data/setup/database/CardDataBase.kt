package com.example.nexuspay.data.setup.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nexuspay.data.setup.database.daos.CardDao
import com.example.nexuspay.domain.model.response.CardEntity

@Database(entities = [CardEntity::class], version = 1)
abstract class CardDataBase : RoomDatabase() {
    abstract fun getCardDao() : CardDao
}