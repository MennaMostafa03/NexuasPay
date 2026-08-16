package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.domain.model.response.UserResponse



data class UserState(
     var user : UserResponse? = null,
     var  isUserLoading : Boolean = false,
     var userErrorMessage : String? = null
)


data class TransactionState(
     var transaction : Map<String?, List<TransactionResponse>>? = null,
     var isTransactionLoading : Boolean = false,
     var transactionErrorMessage : String? = null
)