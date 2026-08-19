package com.example.nexuspay.domain.repository

import com.example.nexuspay.domain.model.response.CardEntity

interface CardRepo {
    suspend fun addCardRepo(cardEntity: CardEntity)
    suspend fun getCardRepo(): List<CardEntity>
}