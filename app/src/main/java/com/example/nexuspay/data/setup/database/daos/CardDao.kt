package com.example.nexuspay.data.setup.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nexuspay.domain.model.response.CardEntity

@Dao
interface CardDao {

    @Insert
    suspend fun addCard(cardEntity: CardEntity)

    @Query("select * from CardEntity")
    suspend fun getCard(): List<CardEntity>
}