package com.example.nexuspay.ui.screens.bottom_nav.card

import AppTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomCard
import com.example.nexuspay.ui.custom_composable.CustomTopAppBar
import com.example.nexuspay.ui.custom_composable.ShimmerCircle
import com.example.nexuspay.ui.routes.Routes
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.UserState
import com.example.nexuspay.ui.theme.Green
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Milky
import kotlinx.coroutines.flow.StateFlow


@Composable
fun CardScreen(navController: NavController, userState: StateFlow<UserState>){
    val userState by userState.collectAsState()
    Column(Modifier.background(Color.Black.copy(0.9f)).fillMaxSize())
    {
        CustomTopAppBar(
            title = "My Cards",
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
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
            }
        )

        Column(
            Modifier.padding(horizontal = 10.dp, vertical = 16.dp),
        )
        {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    "Active Assets",
                    style = AppTypography.displayLarge.copy(color = Milky),
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = LightBlue.copy(0.10f)
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                    ){
                        Text("3 Cards", style = AppTypography.titleSmall.copy(LightBlue))
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

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
                cardHeight = 175.dp,
                header = "NEXUS INFINITE",
                cardNumberLastDigits = "8842",
                cardHolderName = "Menna Mostafa",
                expiryDate = "12/28",
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
                headerColor = Color.White.copy(alpha = 0.75f),
                bodyColor = Color.White,
            )

            Spacer(Modifier.height(40.dp))

            Row(
                Modifier.fillMaxWidth().clickable{
                    navController.navigate(Routes.AddNewCardRoute(name = userState.user?.name))
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Icon(
                    painterResource(R.drawable.ic_add),
                    contentDescription = "add",
                    tint = LightBlue,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    "Add New Card",
                    style = AppTypography.bodyMedium.copy( color = LightBlue)
                )
            }

            Spacer(Modifier.height(30.dp))

            Text(
                "CARD DETAILS",
                style = AppTypography.titleSmall,
                modifier = Modifier.padding(start = 18.dp, bottom = 18.dp)
            )

            CardDetails(
                painterResource(R.drawable.ic_status),
                "Status",
                "Active & Secure",
                Green
            )

            Spacer(Modifier.height(26.dp))

            CardDetails(
                painterResource(R.drawable.ic_money),
                "Physical + Virtual",
                "Active & Secure",
                LightGray
            )

            Spacer(Modifier.height(30.dp))

            Text(
                "SPENDING CONTROLS",
                style = AppTypography.titleSmall,
                modifier = Modifier.padding(start = 18.dp, bottom = 18.dp)
            )

            SpendingControl(
                "Online Purchases",
                "Allow web transactions"
            )

            Spacer(Modifier.height(26.dp))

            SpendingControl(
                "Atm Withdrawals",
                "Enable cash access"
            )
        }
    }
}