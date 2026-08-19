package com.example.nexuspay.domain.usecase.card

import com.example.nexuspay.domain.repository.CardRepo

class GetCardUseCase(val cardRepo: CardRepo) {
    suspend fun invoke() = cardRepo.getCardRepo()
}