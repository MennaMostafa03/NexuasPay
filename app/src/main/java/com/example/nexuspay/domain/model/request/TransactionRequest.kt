package com.example.nexuspay.domain.model.request

import kotlinx.serialization.Serializable

// post data to send money request pass to api
@Serializable
data class TransactionRequest(
	val receiverIdentifier: String? = null,
	val amount: Int? = null,
	val senderIdentifier: String? = null,
	val currency: String? = "EGP",
	val title: String? = null,
)

