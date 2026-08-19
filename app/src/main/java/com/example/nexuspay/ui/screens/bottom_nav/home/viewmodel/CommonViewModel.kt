package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexuspay.domain.usecase.transaction.GetTransactionUseCase
import com.example.nexuspay.domain.usecase.user.GetUserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommonViewModel(
    private val userUseCase: GetUserUseCase,
    private val transactionUseCase: GetTransactionUseCase
) : ViewModel() {

    private var _userState =MutableStateFlow(UserState())
    val userState = _userState.asStateFlow()

    private var _transactionState =MutableStateFlow(TransactionState())
    val transactionState = _transactionState.asStateFlow()

    fun loadUser(){
        viewModelScope.launch {
            _userState.value = _userState.value.copy(isUserLoading = true)
            try {
                val userResult = userUseCase.invoke()
                if (userResult.isSuccess && userResult.getOrNull() != null) {
                    _userState.value = _userState.value.copy(user = userResult.getOrNull())
                } else {
                    _userState.value = _userState.value.copy(userErrorMessage = userResult.exceptionOrNull()?.localizedMessage)

                }
            } catch (e: Throwable){
                _userState.value = _userState.value.copy(userErrorMessage = e.localizedMessage ?: "Something went wrong please try again later")

            } finally {
                _userState.value = _userState.value.copy(isUserLoading = false)
            }
        }
    }

    fun loadTransaction(){
        viewModelScope.launch {
            _transactionState.value = _transactionState.value.copy(isTransactionLoading = true)
            try {
                val transactionResult = transactionUseCase.invoke()

                if (transactionResult.isSuccess && transactionResult.getOrNull() != null) {
                    _transactionState.value = _transactionState.value.copy(transaction =
                        transactionResult.getOrNull()?.
                        sortedByDescending { it.id })
                } else {
                    _transactionState.value = _transactionState.value
                        .copy(transactionErrorMessage = transactionResult.exceptionOrNull()?.localizedMessage)
                }
            } catch (e: Throwable){
                _transactionState.value = _transactionState.value
                    .copy(transactionErrorMessage =  e.localizedMessage ?: "Something went wrong please try again later")

            } finally {
                _transactionState.value = _transactionState.value.copy(isTransactionLoading = false)
            }

        }
    }

    fun loadData(){
        viewModelScope.launch {
            val user = async {
                loadUser()
            }
            val transaction = async {
                loadTransaction()
            }
            user.await()
            transaction.await()
        }
    }

}