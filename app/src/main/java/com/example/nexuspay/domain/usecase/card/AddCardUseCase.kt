package com.example.nexuspay.domain.usecase.card

import com.example.nexuspay.domain.model.response.CardEntity
import com.example.nexuspay.domain.repository.CardRepo

class AddCardUseCase(val cardRepo: CardRepo) {
    suspend fun invoke(cardEntity: CardEntity) = cardRepo.addCardRepo(cardEntity)
}