package com.example.nexuspay.ui.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class  Routes{

    @Serializable
    object HomeRoute : Routes()

    @Serializable
    object CardRoute : Routes()

    @Serializable
    data class AddNewCardRoute(val name: String?): Routes()

    @Serializable
    object TransactionRoute : Routes()

    @Serializable
    data class SendMoneyRoute(val image: String?) :Routes()

}




