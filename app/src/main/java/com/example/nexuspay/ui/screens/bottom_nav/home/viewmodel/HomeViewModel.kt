package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.domain.model.response.UserResponse
import com.example.nexuspay.domain.usecase.GetTransactionUseCase
import com.example.nexuspay.domain.usecase.GetUserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userUseCase: GetUserUseCase,
    private val transactionUseCase: GetTransactionUseCase
) : ViewModel() {

    private var _user : MutableStateFlow<UserResponse?> = MutableStateFlow(null)
    val user : StateFlow<UserResponse?> = _user
    private var  _isUserLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isUserLoading : StateFlow<Boolean> = _isUserLoading

    private var _userErrorMessage : MutableStateFlow<String?> = MutableStateFlow(null)
    val userErrorMessage : StateFlow<String?> = _userErrorMessage

    private var _transaction : MutableStateFlow<Map<String?, List<TransactionResponse>>?> =
        MutableStateFlow(null)
    val transaction : StateFlow<Map<String?, List<TransactionResponse>>?> = _transaction
    private var _isTransactionLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isTransactionLoading : StateFlow<Boolean> = _isTransactionLoading

    private var _transactionErrorMessage : MutableStateFlow<String?> = MutableStateFlow(null)
    val transactionErrorMessage : StateFlow<String?> = _transactionErrorMessage


    fun loadUser(){
        viewModelScope.launch {
            _isUserLoading.value = true
            try {
                val userResult = userUseCase.invoke()
                if (userResult.isSuccess && userResult.getOrNull() != null) {
                    _user.value = userResult.getOrNull()
                } else {
                    _userErrorMessage.value = userResult.exceptionOrNull()?.localizedMessage
                }
            } catch (e: Throwable){
                _userErrorMessage.value = e.localizedMessage ?: "Something went wrong please try again later"
            } finally {
                _isUserLoading.value = false
            }
        }
    }

    fun loadTransaction(){
        viewModelScope.launch {
            _isTransactionLoading.value = true
            try {
                val transactionResult = transactionUseCase.invoke()

                if (transactionResult.isSuccess && transactionResult.getOrNull() != null) {
                    _transaction.value = transactionResult.getOrNull()?.sortedByDescending { it.id }?.take(4)?.sortedByDescending{it.id}?.groupBy { it.date }
                } else {
                    _transactionErrorMessage.value = transactionResult.exceptionOrNull()?.localizedMessage
                }
            } catch (e: Throwable){
                _transactionErrorMessage.value = e.localizedMessage ?: "Something went wrong please try again later"
            } finally {
                _isTransactionLoading.value = false
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