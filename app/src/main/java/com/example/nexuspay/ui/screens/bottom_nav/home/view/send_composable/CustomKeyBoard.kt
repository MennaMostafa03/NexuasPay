package com.example.nexuspay.ui.screens.bottom_nav.home.view.send_composable

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexuspay.R
import com.example.nexuspay.domain.model.request.TransactionEntity
import com.example.nexuspay.ui.custom_composable.CustomButton
import com.example.nexuspay.ui.custom_composable.CustomText
import com.example.nexuspay.ui.custom_composable.CustomTextField
import com.example.nexuspay.ui.theme.Black
import com.example.nexuspay.ui.theme.Blue
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Navy
import com.example.nexuspay.ui.theme.White

val keys = listOf(
    listOf('1', '2', '3'),
    listOf('4', '5', '6'),
    listOf('7', '8', '9'),
    listOf('.', '0')
)

@Composable
fun CustomKeyBoard(
    input : String,
    onClick : (symbol: Char) -> Unit,
    onRemoveClick : () -> Unit,
    onTransferClick : () -> Unit,
    transferLoading : Boolean,
    title : String,
    onValueChange : (String?) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Text("Total Balance", style = AppTypography.titleSmall)

        Spacer(Modifier.height(5.dp))

        CustomText(
            text = "£ $input",
            currencyStyle = AppTypography.bodyLarge.copy(color = Blue),
            numberStyle = AppTypography.bodyLarge,
            decimalStyle = AppTypography.bodyLarge
        )

        Spacer(Modifier.height(5.dp))

        CustomTextField(
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_list),
                    contentDescription = "search",
                    tint = LightGray,
                    modifier = Modifier.size(16.dp)
                )

            },
            placeholder = {
                Text(
                    text = "Add a note (optional)",
                    style = AppTypography.titleSmall.copy(LightGray.copy(0.4f)),
                    textAlign = TextAlign.Center
                )
            },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(Black.copy(0.4f), RoundedCornerShape(16.dp))
                .border(1.dp, White.copy(0.05f), RoundedCornerShape(16.dp)),
            title = title,
            onValueChange = onValueChange
        )

        Spacer(Modifier.height(10.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        )
        {
            keys.forEach { rowsKey ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    rowsKey.forEach { symbol ->
                        KeyButton(symbol, Modifier.weight(1f)) { onClick(it) }
                    }
                    if (rowsKey == keys.last()) {
                        KeyDeleteButton(onRemoveClick, Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        if (transferLoading){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                ){
                Text(
                    text = "Loading...",
                    style = AppTypography.displayLarge.copy(
                        Navy,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        else {
            CustomButton(
                "Confirm Transfer",
                modifier = Modifier
                    .background(Blue),
                icon = painterResource(R.drawable.ic_send)
            ) {
                onTransferClick()
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            "No fee for NexusPay to NexusPay transfers.",
            style = AppTypography.titleSmall.copy(
                color = AppTypography.titleSmall.color.copy(0.6f)
            )
        )
    }
}


