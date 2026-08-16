package com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel

import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.response.CurrentUserItem

data class SendMoneyState(
    var users : List<CurrentUserItem?> = emptyList<CurrentUserItem>(),
    var usersError : String? = null,
    var usersLoading : Boolean = false,
    var requestLoading : Boolean = false,
    var inputValue : String = "0.00",
    var title : String? = "",
    var entity : RequestEntity = RequestEntity()
)