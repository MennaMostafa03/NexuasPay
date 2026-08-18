package com.example.nexuspay.ui.screens.bottom_nav.transaction

import AppTypography
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomTopAppBar
import com.example.nexuspay.ui.custom_composable.CustomTransactionItem
import com.example.nexuspay.ui.custom_composable.ShimmerBox
import com.example.nexuspay.ui.custom_composable.ShimmerCircle
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.CommonViewModel
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.TransactionState
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.UserState
import com.example.nexuspay.ui.theme.Green
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Milky
import com.example.nexuspay.ui.theme.White
import dateFormat
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.viewmodel.koinViewModel
import timeFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TransactionScreen(
    navController: NavController,
    userState: StateFlow<UserState>,
    transactionState: StateFlow<TransactionState>
){

    val userState by userState.collectAsState()
    val transactionState by transactionState.collectAsState()

    Column(
        modifier = Modifier.background(Color.Black.copy(0.9f)).fillMaxSize()
    ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painterResource(R.drawable.ic_search),
                        contentDescription = "notification",
                        tint = LightBlue,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(Modifier.width(15.dp))

                    Icon(
                        painterResource(R.drawable.ic_notification),
                        contentDescription = "notification",
                        tint = White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(Modifier.width(10.dp))
            },
            isLoading = transactionState.isTransactionLoading
        )

        Column(
            Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 16.dp),
        ){
            when {
                transactionState.isTransactionLoading -> {
                    Text(
                        "Transactions",
                        style = AppTypography.displayLarge.copy(color = Milky),
                    )

                    Text(
                        "Review your recent activity across all accounts.",
                        style = AppTypography.bodySmall
                    )

                    Spacer(Modifier.height(25.dp))
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
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
                    ) {
                        item {
                            Row(
                                Modifier.fillMaxWidth().padding(top = 6.dp),
                                Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .clip(RoundedCornerShape(50.dp))
                                        .background(Green)
                                )
                                Spacer(Modifier.width(10.dp))

                                Text("LAST SYNCED 2M AGO", style = AppTypography.titleSmall,)
                            }

                            Spacer(Modifier.height(25.dp))

                            Text("Transactions", style = AppTypography.displayLarge.copy(color = LightGray),)

                            Text("Review your recent activity across all accounts.", style = AppTypography.bodySmall)

                            Spacer(Modifier.height(30.dp))

                        }
                        transactionState.transaction?.groupBy { it.date }?.forEach { entry->
                            val date = entry.key
                            val transactionItems = entry.value
                            val dateHeader = dateFormat(date?:"")
                            item {
                                Text(
                                    dateHeader.uppercase(),
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    style = AppTypography.titleSmall)

                                Spacer(Modifier.height(8.dp))
                            }
                            items(items = transactionItems, key = { it.id!! }) { item ->

                                CustomTransactionItem(
                                    item = item,
                                    iconColor = if(item.type == "SENT") Green else LightBlue,
                                    text = LocalDate.parse(item.date)
                                        .format(DateTimeFormatter.ofPattern("MMM dd"))+ "  •  " + timeFormat(item.time),
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .background(Color.Transparent),
                                    cornerSize = 16
                                )
                            }
                            item {
                                Spacer(Modifier.height(12.dp))
                            }
                        }
                        item {
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Spacer(Modifier.height(18.dp))
                                Icon(
                                    painterResource(R.drawable.ic_history),
                                    contentDescription = "history",
                                    tint = LightGray,
                                    modifier = Modifier.width(30.dp).height(25.dp),
                                )
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    "End of recent History.",
                                    style = AppTypography.bodySmall
                                )

                                Spacer(Modifier.height(30.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}