package com.example.nexuspay.ui.screens.bottom_nav.card.viewmodel

import CardState
import androidx.core.util.rangeTo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexuspay.domain.model.response.CardEntity
import com.example.nexuspay.domain.usecase.card.AddCardUseCase
import com.example.nexuspay.domain.usecase.card.GetCardUseCase
import com.example.nexuspay.utils.formatExpiryDate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardViewModel(
    private val addCardUseCase: AddCardUseCase,
    private val getCardUseCase: GetCardUseCase
) : ViewModel() {
    private var _cardState = MutableStateFlow(CardState())
    val cardState = _cardState.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    fun addCardDetails(){
        viewModelScope.launch {
            _cardState.value = _cardState.value.copy(isLoading = true)
            addCardUseCase.invoke(_cardState.value.cardEntity)
            _cardState.value = _cardState.value.copy(isLoading = false)
        }
        _cardState.value = _cardState.value.copy(
            cardEntity = CardEntity()
        )
    }

    fun getCardDetails(){
        viewModelScope.launch {
            try {
                val card = getCardUseCase.invoke()
                if (card.isEmpty()){
                    _cardState.value = _cardState.value.copy(cardError = "NO ACTIVE CARDS")
                }else{
                    _cardState.value = _cardState.value.copy(card = card)
                }
            }catch (e: Exception){
                _cardState.value = _cardState.value.copy(cardError = e.localizedMessage)
            }
        }
    }

    fun onCardNumberChange(newValue: String) {
        if(newValue.length <=16) {
            val cardType = when {
                newValue.startsWith("4") -> "Visa Card"
                newValue.startsWith("5") -> "Master Card"
                else -> ""
            }

            _cardState.value = _cardState.value.copy(
                cardEntity = _cardState.value.cardEntity.copy(
                    cardNumber = newValue,
                    cardType = cardType
                )
            )
        }

    }

    fun onCardNameChange(newValue: String) {
        _cardState.value = _cardState.value.copy(
            cardEntity = _cardState.value.cardEntity.copy(
                cardName = newValue
            )
        )
    }

    fun onExpireDateChange(newValue: String) {
        if (
            newValue.length <= 5 &&
            newValue.all { it.isDigit() || it == '/' }
        ) {
            _cardState.value = _cardState.value.copy( formatedExpireDate = newValue)
            _cardState.value = _cardState.value.copy(
                cardEntity = _cardState.value.cardEntity.copy(
                    expireDate = newValue
                )
            )
        }
    }

    fun onCVVChange(newValue: String) {
        if(newValue.length <=4) {
            _cardState.value = _cardState.value.copy(
                cardEntity = _cardState.value.cardEntity.copy(
                    cvv = newValue
                )
            )
        }
    }

    fun validation() {
        val entity = _cardState.value.cardEntity


        val cardNumberRegex =
            Regex("^\\d{4}\\d{4}\\d{4}\\d{4}$")

        val expireDateRegex =
            Regex("^(0[1-9]|1[0-2])/\\d{2}$")

        val cvvRegex =
            Regex("^\\d{3,4}$")

        val cardNameRegex =
            Regex("^[A-Za-z]+\\s[A-Za-z]+$")


        viewModelScope.launch {

            if (entity.cardNumber.isEmpty()) {
                _message.emit("Fill Card Number")
            } else if (entity.cardName.isEmpty()) {
                _message.emit("Fill Card Name")
            } else if (entity.expireDate.isEmpty()) {
                _message.emit("Fill Expire Date")
            } else if (entity.cvv.isEmpty()) {
                _message.emit("Fill CVV")
            }

            val formatedExpireDate = formatExpiryDate(entity.expireDate)


            if (!entity.cardNumber.matches(cardNumberRegex)) {
                _message.emit("Digits only available in card number field")
            } else if (!entity.cardName.matches(cardNameRegex)) {
                _message.emit("Letters only available in cardholder name field")
            } else if (!formatedExpireDate.matches(expireDateRegex)){
                _message.emit("Digits and / char available in expiry date field")
            } else if (!entity.cvv.matches(cvvRegex)){
                _message.emit("Only Digits in range 3 to 4")
            } else {
                _message.emit("")
            }
        }
    }
}