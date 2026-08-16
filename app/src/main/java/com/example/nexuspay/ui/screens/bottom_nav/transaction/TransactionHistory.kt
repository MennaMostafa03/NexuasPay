package com.example.nexuspay.ui.screens.bottom_nav.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.ui.custom_composable.CustomTransactionItem
import timeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TransactionHistory (
    modifier: Modifier,
    iconColor : Color,
    item : TransactionResponse

){
    Column(
        Modifier.fillMaxWidth()
    ) {
        CustomTransactionItem(
            item = item,
            iconColor = iconColor,
            text = LocalDate.parse(item.date)
                .format(DateTimeFormatter.ofPattern("MMM dd"))+ " . " + timeFormat(item.time),
            modifier = modifier
                .background(Color.Transparent),
            cornerSize = 16
        )
    }
}