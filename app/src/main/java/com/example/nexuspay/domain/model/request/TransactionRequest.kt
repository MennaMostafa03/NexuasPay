package com.example.nexuspay.domain.model.request

import kotlinx.serialization.Serializable


@Serializable
data class TransactionRequest(
	val receiverIdentifier: String? = null,
	val amount: Int? = null,
	val senderIdentifier: String? = null,
	val currency: String? = "EGP",
	val title: String? = null,
)

