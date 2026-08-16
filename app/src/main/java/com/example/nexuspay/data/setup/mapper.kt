package com.example.nexuspay.data.setup

import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.domain.model.request.TransactionRequest

fun TransactionEntity.toRequest() = TransactionRequest(
    senderIdentifier = senderIdentifier,
    receiverIdentifier = receiverIdentifier?: "",
    currency = currency ?:"",
    title = title ?: "",
    amount = amount
)
