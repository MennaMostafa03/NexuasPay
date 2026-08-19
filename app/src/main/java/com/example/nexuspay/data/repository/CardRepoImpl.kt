package com.example.nexuspay.data.repository

import com.example.nexuspay.data.local_ds.card.CardLocalData
import com.example.nexuspay.domain.model.response.CardEntity
import com.example.nexuspay.domain.repository.CardRepo

class CardRepoImpl(
    val localData: CardLocalData
)  : CardRepo {
    override suspend fun addCardRepo(cardEntity: CardEntity) {
        localData.addCardData(cardEntity)
    }

    override suspend fun getCardRepo(): List<CardEntity> {
        return localData.getCardData()
    }
}