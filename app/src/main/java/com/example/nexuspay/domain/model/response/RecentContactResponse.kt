package com.example.nexuspay.domain.model.response

import kotlinx.serialization.Serializable


@Serializable
data class CurrentUserItem(
	val identifier: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val avatar: String? = null
)

