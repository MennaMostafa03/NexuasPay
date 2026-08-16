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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomTopAppBar
import com.example.nexuspay.ui.custom_composable.ShimmerBox
import com.example.nexuspay.ui.custom_composable.ShimmerCircle
import com.example.nexuspay.ui.routes.Routes
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.CommonViewModel
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavController){
    val viewModel = koinViewModel<CommonViewModel>()
    val userState by viewModel.userState.collectAsState()
    val transactionState by viewModel.transactionState.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadData() }

    Column(Modifier.background(Color.Black.copy(0.9f)).fillMaxSize())
    {
        CustomTopAppBar(
            title = userState.user?.name?: "",
            navigationIcon = {
                if (userState.isUserLoading) {
                    ShimmerCircle(size = 40.dp, modifier = Modifier.padding(horizontal = 10.dp))
                } else {
                    AsyncImage(
                        model = userState.user?.avatar ?: "",
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(50.dp))
                    )
                }
            },
            action = {
                Icon(
                    painterResource(R.drawable.ic_notification),
                    contentDescription = "notification",
                    tint = LightGray,
                    modifier = Modifier.width(16.dp).height(20.dp)
                )
                Spacer(Modifier.width(10.dp))
            },
            isLoading = transactionState.isTransactionLoading
        )

        Column(
            Modifier.padding(horizontal = 10.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            when {
                userState.isUserLoading -> {
                    ShimmerBox(
                        Modifier
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
                    )
                    Spacer(Modifier.height(28.dp))
                    ShimmerBox(
                        Modifier
                            .clip(RoundedCornerShape(40.dp))
                            .width(150.dp)
                            .height(30.dp)
                            .border(1.dp, LightBlue.copy(0.2f), RoundedCornerShape(40.dp))
                    )
                    Spacer(Modifier.height(28.dp))
                }

                userState.userErrorMessage != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = userState.userErrorMessage!!,
                            style = AppTypography.displayLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                userState.user != null -> {
                    Balance(userState.user?.balance ?: 0, userState.user?.currency)
                    Spacer(Modifier.height(28.dp))
                    SendButton{
                        navController.navigate(Routes.SendMoneyRoute(userState.user?.avatar))
                    }
                    Spacer(Modifier.height(28.dp))
                }
            }
            when {
                transactionState.isTransactionLoading -> {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            "Recent Transactions",
                            style = AppTypography.displayMedium,
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                    LazyColumn {
                        items(4) {
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                transactionState.transactionErrorMessage != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = transactionState.transactionErrorMessage!!,
                            style = AppTypography.displayLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                !transactionState.transaction.isNullOrEmpty() -> {
                    TransactionView(transactionState.transaction?.take(4)?.groupBy { it.date})
                }
            }
        }
    }
}