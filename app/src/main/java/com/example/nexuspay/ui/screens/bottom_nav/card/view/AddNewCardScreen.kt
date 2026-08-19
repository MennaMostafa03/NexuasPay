package com.example.nexuspay.ui.screens.bottom_nav.card.view

import AppTypography
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomButton
import com.example.nexuspay.ui.custom_composable.CustomCard
import com.example.nexuspay.ui.custom_composable.CustomTextField
import com.example.nexuspay.ui.custom_composable.CustomTopAppBar
import com.example.nexuspay.ui.screens.bottom_nav.card.viewmodel.CardViewModel
import com.example.nexuspay.ui.theme.Black
import com.example.nexuspay.ui.theme.Gray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Navy
import com.example.nexuspay.ui.theme.White
import org.koin.compose.koinInject

@Composable
fun AddNewCardScreen(navController: NavController, name: String?) {

    val context = LocalContext.current
    val viewModel = koinInject<CardViewModel>()
    val state by viewModel.cardState.collectAsState()

    LaunchedEffect(Unit)
    {
        viewModel.message.collect { it ->
            if (it.isEmpty()) {
                viewModel.addCardDetails()
            }else {
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Color.Black.copy(0.9f))
            .fillMaxSize()
    ) {
        CustomTopAppBar(
            title = name ?: "",
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "back arrow",
                    tint = LightBlue,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(20.dp)
                        .clickable { navController.popBackStack() }
                )
            }
        )
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(bottom = 70.dp),
        )
        {
            item{
                Text(
                    text = "Add New Card",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = AppTypography.displayMedium.copy(fontSize = 34.sp)
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Link a secure payment method to your digital vault.",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = AppTypography.bodySmall
                )

                Spacer(Modifier.height(20.dp))

                CustomCard(
                    connectionIconTint = LightBlue.copy(alpha = 0.6f),
                    middleContent = {
                        Spacer(Modifier.height(5.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_cards),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = LightBlue
                        )
                    },
                    header = "NEXUS ELITE"
                )

                Spacer(Modifier.height(24.dp))

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, LightGray, RoundedCornerShape(16.dp))
                        .background(White),
                    title = state.cardEntity.cardNumber ,
                    placeholder = {
                        Text(
                            text = "0000  0000  0000  0000",
                            style = AppTypography.bodySmall.copy(Black)
                        )
                    },
                    label = "Card Number",
                    textColor = Black
                ) { viewModel.onCardNumberChange(it) }

                Spacer(Modifier.height(16.dp))

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, LightGray, RoundedCornerShape(16.dp))
                        .background(White),
                    title = state.cardEntity.cardName ,
                    placeholder = {
                        Text(
                            text = "ENTER FULL NAME",
                            style = AppTypography.bodySmall.copy(Black)
                        )
                    },
                    label = "Cardholder Name",
                    textColor = Black
                ) { viewModel.onCardNameChange(it) }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                )
                {
                    Column(Modifier.weight(1f)) {
                        CustomTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .border(1.dp, LightGray, RoundedCornerShape(16.dp))
                                .background(White),
                            title = state.cardEntity.expireDate,
                            placeholder = {
                                Text(
                                    text = "MM/YY",
                                    style = AppTypography.bodySmall.copy(Gray)
                                )
                            },
                            label = "Expiry Date",
                            textColor = Black
                        ) { viewModel.onExpireDateChange(it)}
                    }

                    Column(Modifier.weight(1f)) {
                        CustomTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .border(1.dp, LightGray, RoundedCornerShape(16.dp))
                                .background(White),
                            title = state.cardEntity.cvv,
                            placeholder = {
                                Text(
                                    text = "••••",
                                    style = AppTypography.bodySmall.copy(Black)
                                )
                            },
                            label = "CVV",
                            textColor = Black,
                            visualTransformation = PasswordVisualTransformation()
                        ) { viewModel.onCVVChange(it) }
                    }
                }

                Spacer(Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    border = BorderStroke(1.dp, LightGray.copy(alpha = 0.2f)),
                    colors = CardDefaults.cardColors(containerColor = Gray.copy(alpha = 0.5f))
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_security),
                                contentDescription = null,
                                tint = LightBlue,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(Modifier.width(14.dp))

                        Text(
                            text = "Your payment information is encrypted\nand never stored on our servers.\nTransactions are processed through an\nindustry-standard secure gateway.",
                            style = AppTypography.bodySmall,
                            lineHeight = 22.sp
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                if (state.isLoading){
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
                } else {
                    CustomButton(
                        text = "Add Card",
                        modifier = Modifier
                            .fillMaxWidth()
                            .dropShadow(
                                shape = RoundedCornerShape(20.dp),
                                shadow = Shadow(
                                    radius = 10.dp,
                                    spread = 6.dp,
                                    color = LightBlue.copy(alpha = 0.3f),
                                    offset = DpOffset(0.dp, 0.dp)
                                )
                            ),
                        containerColor = LightBlue,
                        elevation = 8.dp,
                        onClick = {
                            viewModel.validation()
                        },
                    )
                }
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}