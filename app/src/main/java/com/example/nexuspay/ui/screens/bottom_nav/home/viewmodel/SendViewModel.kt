package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexuspay.data.setup.api.USER_IDENTIFIER
import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.usecase.transaction.SaveRequestUseCase
import com.example.nexuspay.domain.usecase.transaction.GetAllUserUseCase
import com.example.nexuspay.utils.exception.TransactionResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SendViewModel(
    private val userUseCase: GetAllUserUseCase,
    private val saveRequestUseCase: SaveRequestUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(SendMoneyState())
    val state = _state.asStateFlow()

    private val _message = MutableSharedFlow<String>()
    val message = _message.asSharedFlow()

    fun loadUsers(){
        viewModelScope.launch{
            _state.value = _state.value.copy(usersLoading = true)
            try {
                val transactionResult = userUseCase.invoke()
                if (transactionResult.isSuccess && transactionResult.getOrNull() != null) {
                    transactionResult.getOrNull()
                        ?.let { _state.value = _state.value.copy(users = it.filter { it -> it.identifier != USER_IDENTIFIER }) }
                } else {
                    _state.value = _state.value.copy(usersError = transactionResult.exceptionOrNull()?.localizedMessage)
                }
            } catch (e: Throwable){
                _state.value = _state.value.copy(usersError = e.localizedMessage ?: "Something went wrong please try again later")
            } finally {
                _state.value = _state.value.copy(usersLoading = false)
            }
        }
    }

    fun onKeyboardClick(symbol: Char) {
        val current = _state.value.inputValue
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
        _state.value = _state.value.copy(inputValue = newValue)
        val amount = newValue.toDoubleOrNull()?.times(100)?.toInt() ?: 0
        _state.value.entity = _state.value.entity.copy(amount = amount )
    }

    fun onRemoveClick() {
        val current = _state.value.inputValue
        if (current == "0.00") return

        val newValue = current.dropLast(1).ifEmpty { "0.00" }
        _state.value = _state.value.copy(inputValue = newValue)

        val amount = newValue.toDoubleOrNull()?.times(100)?.toInt() ?: 0
        _state.value.entity = _state.value.entity.copy(amount = amount )
    }

    fun onValueChange(newTitle: String?) {
        _state.value = _state.value.copy(title = newTitle?:"")
        _state.value = _state.value.copy(
            title = newTitle,
            entity = _state.value.entity.copy(
                title = newTitle
            )
        )
    }

    fun onContactSelected(identifier: String?) {
        _state.value.entity = _state.value.entity.copy(receiverIdentifier = identifier)
    }

    // when i click on transfer button
    fun onTransferClick() {
        sendMoney(_state.value.entity.copy(id = null))
        _state.value = _state.value.copy(
            entity = RequestEntity(),
            inputValue = "0.00",
            title = ""
        )
    }
    fun sendMoney(entity: RequestEntity) {
        viewModelScope.launch {
            _state.value = _state.value.copy(requestLoading = true)
            val transferResult = saveRequestUseCase.invoke(entity)
            _state.value = _state.value.copy(requestLoading = false)
            when (transferResult) {
                TransactionResult.Pending ->
                    _message.emit("Your transfer is pending and will be processed automatically.")

                TransactionResult.Success ->
                    _message.emit("Transfer successful.")

                TransactionResult.Failed ->
                    _message.emit("Transfer failed. Please check the transaction details.")
            }
        }
    }
}