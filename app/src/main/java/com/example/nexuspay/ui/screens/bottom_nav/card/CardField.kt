package com.example.nexuspay.ui.screens.bottom_nav.card

data class CardField(
    val label: String,
    val placeholder: String
)

val fields = listOf(
    CardField(label = "Card Number", placeholder = "0000  0000  0000  0000"),
    CardField(label = "Cardholder Name", placeholder = "ENTER FULL NAME"),
    CardField(label = "MM/YY", placeholder = "Expiry Date"),
    CardField(label = "CVV", placeholder = "••••")
)