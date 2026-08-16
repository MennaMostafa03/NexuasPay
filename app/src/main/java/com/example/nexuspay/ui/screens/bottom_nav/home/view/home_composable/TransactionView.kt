package com.example.nexuspay.ui.screens.bottom_nav.home.view.home_composable

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexuspay.ui.custom_composable.CustomTransactionItem
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.White
import dateFormat
import timeFormat

@Composable
fun TransactionView(
    transaction : Map<String? , List<TransactionResponse>>?
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text("Recent Transactions",
            style = AppTypography.displayMedium)

        Text("See All",
            style = AppTypography.titleSmall.copy(LightBlue))
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = 60.dp)
    ) {
        transaction?.forEach { entry ->
            val date = entry.key
            val transactionItems = entry.value
            val dateHeader = dateFormat(date?:"")

            items(items = transactionItems, key = { it.id!! }){ item ->
                CustomTransactionItem(
                    item = item,
                    iconColor = White,
                    text = "${ if (dateHeader == "Today") timeFormat(item.time) else dateHeader} " + ". ${item.type}",
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(DarkGray.copy(0.4f))
                        .border(1.dp, Color.White.copy(0.08f), RoundedCornerShape(12.dp)),
                    state = item.state,
                    cornerSize = 50
                )
            }
        }
    }
}

