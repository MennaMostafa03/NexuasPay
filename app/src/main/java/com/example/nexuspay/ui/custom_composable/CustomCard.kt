package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexuspay.R

@Composable
fun CustomCard(
    backgroundContent: @Composable () -> Unit,
    connectionIconTint: Color,
    middleContent: @Composable () -> Unit,
    cardHeight: Dp,
    header: String,
    cardNumberLastDigits: String? = "••••",
    cardLogo: Painter? = null,
    cardHolderName: String,
    expiryDate: String,
    contentPadding: PaddingValues,
    headerColor: Color ,
    bodyColor: Color, )
{


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // عشان الـ backgroundContent يتحكم
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            backgroundContent()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = header.uppercase(),
                        style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = headerColor
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_connection),
                        contentDescription = "Contactless",
                        tint = connectionIconTint,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(Modifier.height(5.dp))

                middleContent()

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "••••  ••••  ••••  ${cardNumberLastDigits ?: "••••"}",
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 26.sp,
                        letterSpacing = 2.sp
                    ),
                    color = bodyColor
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = "CARD HOLDER",
                            style = AppTypography.titleSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = headerColor
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = cardHolderName?.uppercase() ?: "YOUR NAME",
                            style = AppTypography.bodyMedium.copy(fontSize = 14.sp),
                            color = bodyColor
                        )
                    }

                    Column {
                        Text(
                            text = "EXPIRES",
                            style = AppTypography.titleSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = headerColor
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = expiryDate ?: "MM/YY",
                            style = AppTypography.bodyMedium.copy(fontSize = 14.sp),
                            color = bodyColor
                        )
                    }

                    cardLogo?.let { logo ->
                        Image(
                            painter = logo,
                            contentDescription = "Card Brand",
                            modifier = Modifier
                                .width(40.dp)
                                .height(24.dp)
                        )
                    }
                }
            }
        }
    }
}