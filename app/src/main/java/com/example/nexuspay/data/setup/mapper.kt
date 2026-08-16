package com.example.nexuspay.data.setup

import com.example.nexuspay.domain.model.request.RequestEntity
import com.example.nexuspay.domain.model.request.TransactionRequest

fun RequestEntity.toRequest() = TransactionRequest(
    senderIdentifier = senderIdentifier,
    receiverIdentifier = receiverIdentifier?: "",
    currency = currency ?:"",
    title = title ?: "",
    amount = amount
)
