package com.example.nexuspay.ui.screens.bottom_nav.home.view.home_composable

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import balanceFormat
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomText
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.Green
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray

@Composable
fun Balance(
    balance: Int?,
    currency: String?,
){
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(0.65f),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(1.dp, LightGray.copy(0.2f), RoundedCornerShape(12.dp))
            .dropShadow(
                shape = RoundedCornerShape(20.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 6.dp,
                    color = LightBlue.copy(0.15f),
                    offset = DpOffset(x = 0.dp, 0.dp)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DarkGray.copy(0.4f))
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            Text("Total Balance", style = AppTypography.titleSmall)

            CustomText(
                text = balanceFormat(balance?: 0, currency = currency),
                currencyStyle = AppTypography.bodyLarge,
                numberStyle = AppTypography.bodyLarge,
                decimalStyle = AppTypography.displayMedium.copy(LightGray)
            )

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Green.copy(0.10f)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                ){
                    Icon(
                        painterResource(R.drawable.ic_increase),
                        contentDescription = "increase",
                        tint = Green,
                        modifier = Modifier.width(12.dp).height(7.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text("+2.4%", style = AppTypography.titleSmall.copy(Green))
                }

            }
        }
    }
}