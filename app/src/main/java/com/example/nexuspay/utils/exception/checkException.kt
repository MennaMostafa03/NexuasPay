package com.example.nexuspay.utils.exception

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException

fun checkException(exception: Throwable) : Boolean {
    return when (exception) {
        is IOException,
        is SocketTimeoutException -> true
        is ServerResponseException -> true
        is ClientRequestException -> false
        is JsonConvertException -> false
        else -> false
    }
}


sealed interface TransactionResult {
    data object Success : TransactionResult
    data object Failed: TransactionResult
    data object Pending : TransactionResult
}