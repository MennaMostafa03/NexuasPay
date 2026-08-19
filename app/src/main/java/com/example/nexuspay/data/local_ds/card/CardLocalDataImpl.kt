package com.example.nexuspay.data.local_ds.card

import com.example.nexuspay.data.setup.database.daos.CardDao
import com.example.nexuspay.domain.model.response.CardEntity

class CardLocalDataImpl(val dao: CardDao): CardLocalData {
    override suspend fun addCardData(cardEntity: CardEntity) {
        dao.addCard(cardEntity)
    }

    override suspend fun getCardData(): List<CardEntity> {
       return dao.getCard()
    }
}