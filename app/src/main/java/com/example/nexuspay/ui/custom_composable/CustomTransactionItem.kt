package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import balanceFormat
import com.example.nexuspay.R
import com.example.nexuspay.domain.model.response.TransactionResponse
import com.example.nexuspay.ui.theme.Gray
import com.example.nexuspay.ui.theme.Green
import com.example.nexuspay.ui.theme.White


@Composable
fun CustomTransactionItem(
    item: TransactionResponse,
    modifier: Modifier,
    state: String? = null,
    text: String?,
    iconColor: Color,
    cornerSize: Int
){

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier= Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(cornerSize.dp))
                    .background(Gray),
                contentAlignment = Alignment.Center
            )
            {
                Icon(
                    painterResource(R.drawable.ic_money),
                    contentDescription = "money",
                    tint = iconColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(16.dp)
                        .height(20.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            )
            {
                Text(if (item.description.isNullOrEmpty()) "No description" else item.description,
                    style = AppTypography.bodyMedium)

                Text("$text" ,
                    style = AppTypography.bodySmall)
            }

            Spacer(Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.End,
            )
            {
                Text(balanceFormat(item.amount?:0, item.type, item.currency),
                    style = AppTypography.bodyMedium
                        .copy(if(item.type == "SENT") Green else White)
                )

                if (state != null){
                    Text(state.uppercase(), style = AppTypography.bodySmall)
                }
            }
        }
    }
}