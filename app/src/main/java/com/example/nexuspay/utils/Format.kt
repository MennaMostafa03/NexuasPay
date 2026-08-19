@file:Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.nexuspay.utils


import android.annotation.SuppressLint
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