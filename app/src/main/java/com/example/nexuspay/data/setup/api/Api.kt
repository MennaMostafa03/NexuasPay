package com.example.nexuspay.data.setup.api

import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json


const val USER_IDENTIFIER = "01020050902"

fun createHttpClient() : HttpClient {
    return HttpClient{
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }

        install(Logging){
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        defaultRequest {
            url("https://androidinternbackend-production.up.railway.app/api/")
            contentType(ContentType.Application.Json)
        }
    }
}