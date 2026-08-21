package com.example.nexuspay.ui.screens.bottom_nav.card.view

import AppTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexuspay.R
import com.example.nexuspay.domain.model.response.CardEntity
import com.example.nexuspay.ui.custom_composable.CustomCard
import com.example.nexuspay.ui.theme.Gray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.Milky

@Composable
fun AddCarousel(cards: List<CardEntity>) {

    val pagerState = rememberPagerState (pageCount = { cards.size } )

    HorizontalPager(
        state = pagerState
    ) {page ->
        val card = cards[page]

        Column(
            Modifier.fillMaxWidth()
        ) {
            CustomCard(
                backgroundContent = {
                    Image(
                        painter = painterResource(R.drawable.card_bg),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                },
                connectionIconTint = Color.White,
                middleContent = {
                    Text(
                        text = "Priority Debit",
                        style = AppTypography.bodyLarge.copy(fontSize = 20.sp)
                    )
                },
                header = card.cardType,
                cardNumbers = "••••  ••••  •••• "+card.cardNumber.takeLast(4),
                cardHolderName = card.cardName,
                expiryDate = card.expireDate,
                cardLogo = if (card.cardType == "Master Card") painterResource(R.drawable.masterlogo) else painterResource(R.drawable.visa)
            )

            Spacer(Modifier.height(18.dp))

            Row (Modifier
                .height(24.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                repeat(cards.size){
                    val  color = if (pagerState.currentPage == it) LightBlue else Milky
                    Box(Modifier
                        .padding(horizontal = 2.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color)
                        .size(10.dp, 5.dp)
                    )
                }
            }
        }
    }
}

