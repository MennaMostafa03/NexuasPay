package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexuspay.data.setup.api.USER_IDENTIFIER
import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem
import com.example.nexuspay.domain.usecase.CreateTransactionUseCase
import com.example.nexuspay.domain.usecase.CurrentTransactionUseCase
import com.example.nexuspay.utils.exception.TransactionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SendViewModel(
    private val currentTransactionUseCase: CurrentTransactionUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase
) : ViewModel() {

    private var _recentContact : MutableStateFlow<List<CurrentUserItem>?> = MutableStateFlow(emptyList())
    val recentContact = _recentContact.asStateFlow()
    private var _contactErrorMessage : MutableStateFlow<String?> = MutableStateFlow(null)
    val contactErrorMessage  = _contactErrorMessage.asStateFlow()

    private var _isContactLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isContactLoading =  _isContactLoading.asStateFlow()


    private var _isTransferLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isTransferLoading = _isTransferLoading.asStateFlow()
    private var _message : MutableStateFlow<String?> = MutableStateFlow(null)
    val message  = _message.asStateFlow()

    private val _inputValue = MutableStateFlow("0.00")
    val inputValue = _inputValue.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _entity = MutableStateFlow(TransactionEntity())
    val entity = _entity.asStateFlow()

    fun loadRecentUser(){
        viewModelScope.launch{
            _isContactLoading.value = true
            try {
                val transactionResult = currentTransactionUseCase.invoke()
                if (transactionResult.isSuccess && transactionResult.getOrNull() != null) {
                    _recentContact.value = transactionResult.getOrNull()?.filter { it.identifier != USER_IDENTIFIER }
                } else {
                _contactErrorMessage.value = transactionResult.exceptionOrNull()?.localizedMessage
                }
            } catch (e: Throwable){
                _contactErrorMessage.value = e.localizedMessage ?: "Something went wrong please try again later"
            } finally {
                _isContactLoading.value = false
            }
        }
    }

    fun transferTransaction(entity: TransactionEntity) {
        viewModelScope.launch {
            clearMessage()
            _isTransferLoading.value = true

            val transferResult = createTransactionUseCase.invoke(entity)

            _isTransferLoading.value = false

            _message.value = when (transferResult) {
                TransactionResult.Pending -> "Your transfer is pending and will be processed automatically."
                TransactionResult.Success -> "Transfer successful."
                TransactionResult.Failed  -> "Transfer failed. Please check the transaction details."
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }

    fun onKeyboardClick(symbol: Char) {
        val current = _inputValue.value
        val newValue = when {
            symbol.isDigit() -> {
                if (current == "0.00") symbol.toString()
                else current + symbol
            }
            symbol == '.' -> {
                when {
                    current == "0.00" -> "0."
                    !current.contains(".") -> "$current."
                    else -> current
                }
            }
            else -> current
        }
        _inputValue.value = newValue
        val amountInCents = newValue.toDoubleOrNull()?.times(100)?.toInt() ?: 0
        _entity.value = _entity.value.copy(amount = amountInCents)
    }

    fun onRemoveClick() {
        val current = _inputValue.value
        if (current == "0.00") return

        val newValue = current.dropLast(1).ifEmpty { "0.00" }
        _inputValue.value = newValue

        val amountInCents = newValue.toDoubleOrNull()?.times(100)?.toInt() ?: 0
        _entity.value = _entity.value.copy(amount = amountInCents)
    }

    fun onAmountChange(newTitle: String?) {
        _title.value = newTitle ?: ""
        _entity.value = _entity.value.copy(title = newTitle)
    }

    fun onContactSelected(identifier: String?) {
        _entity.value = _entity.value.copy(receiverIdentifier = identifier)
    }

    fun onTransferClick() {
        viewModelScope.launch {
            transferTransaction(_entity.value.copy(id = null))
            _entity.value = TransactionEntity()
            _inputValue.value = "0.00"
            _title.value = ""
        }
    }
}