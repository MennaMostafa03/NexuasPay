package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexuspay.R
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.Gray
import com.example.nexuspay.ui.theme.LightBlue

@Composable
fun CustomCard(
    backgroundContent: (@Composable () -> Unit)? = null,
    connectionIconTint: Color,
    middleContent: @Composable () -> Unit,
    header: String= "",
    cardNumbers: String = "",
    cardLogo: Painter?= null,
    cardHolderName: String = "",
    expiryDate: String = "",
    )
{

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // عشان الـ backgroundContent يتحكم
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if(backgroundContent?.invoke() == null){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(DarkGray, Gray),
                                start = Offset(0f, Float.POSITIVE_INFINITY),
                                end = Offset(Float.POSITIVE_INFINITY, 0f)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_image),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = LightBlue.copy(0.2f)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = header.ifEmpty { "NEXUS INFINITE" }.uppercase() ,
                        style = AppTypography.titleSmall.copy(fontWeight = FontWeight.Bold),
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
                    text = cardNumbers.ifEmpty { "••••  ••••  •••• ••••" } ,
                    style = AppTypography.bodyLarge.copy(
                        fontSize = 26.sp,
                        letterSpacing = 2.sp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Column {
                        Text(
                            text = "CARD HOLDER",
                            style = AppTypography.titleSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = cardHolderName.ifEmpty{"YOUR NAME"}.uppercase(),
                            style = AppTypography.bodyMedium.copy(fontSize = 14.sp),
                        )
                    }

                    Column {
                        Text(
                            text = "EXPIRES",
                            style = AppTypography.titleSmall.copy(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = expiryDate.ifEmpty { "MM/YY" },
                            style = AppTypography.bodyMedium.copy(fontSize = 14.sp),
                        )
                    }

                    cardLogo?.let { logo ->
                        Image(
                            painter = logo,
                            contentDescription = "Card Brand",
                            modifier = Modifier.size(
                               width =  if(logo == painterResource(R.drawable.visa))  90.dp else 50.dp,
                               height = if(logo == painterResource(R.drawable.visa))  50.dp else 30.dp
                            )
                        )
                    }
                }
            }
        }
    }
}