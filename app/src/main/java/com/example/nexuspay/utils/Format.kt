@file:Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.nexuspay.utils


import android.annotation.SuppressLint
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone


fun dateFormat(date: String) : String{
    val localDate = LocalDate.parse(date)
    val formatter = DateTimeFormatter.ofPattern("MMM dd")
    var dateCheck = ""
    when (localDate) {
        LocalDate.now() ->
            dateCheck =  "Today"
        LocalDate.now().minusDays(1) ->
            dateCheck =  "Yesterday"
        else -> dateCheck = localDate.format(formatter)
    }
    return dateCheck
}

fun timeFormat(time: String?) : String? {

    val parseTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val formatter = SimpleDateFormat("h:mm a", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("GMT+03:00")
    }
    val parsedDate = parseTime.parse(time)
    return parsedDate?.let { formatter.format(it) } ?: time
}

fun balanceFormat(balance: Int, type: String?="", currency: String?) : String{
    val amount = balance.div(100.0)
    val amountFormat =  "%.2f".format(amount)
    val newCurrency = if (currency.isNullOrEmpty()) "EGP" else currency
    return when (type) {
        "SENT" -> "- ${"$newCurrency $amountFormat"}"
        "RECEIVED" -> "+ ${"$newCurrency $amountFormat"}"
        else -> "$newCurrency $amountFormat"
    }
}

@SuppressLint("DefaultLocale")
fun formatExpiryDate(value: String): String {
    val parts = value.split("/")

    if (parts.size != 2) return value

    val month = parts[0].toIntOrNull() ?: return value
    val year = parts[1]

    return String.format("%02d/%s", month, year)
}


fun formatCardNumber(value : String) : String {
    return  value
        .filter { it.isDigit() }
        .take(16)
        .chunked(4)
        .joinToString(" ")
}


class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: androidx.compose.ui.text.AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        val formatted = trimmed.chunked(4).joinToString(" ")

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val spaces = when {
                    offset <= 4 -> 0
                    offset <= 8 -> 1
                    offset <= 12 -> 2
                    else -> 3
                }
                return (offset + spaces).coerceAtMost(formatted.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset - (offset / 5).coerceAtMost(3)
            }
        }

        return TransformedText(androidx.compose.ui.text.AnnotatedString(formatted), offsetMapping)
    }
}