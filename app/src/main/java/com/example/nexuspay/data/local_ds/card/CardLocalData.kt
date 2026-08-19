package com.example.nexuspay.data.local_ds.card

import com.example.nexuspay.domain.model.response.CardEntity

interface CardLocalData {

    suspend fun addCardData(cardEntity: CardEntity)

    suspend fun getCardData() : List<CardEntity>
}