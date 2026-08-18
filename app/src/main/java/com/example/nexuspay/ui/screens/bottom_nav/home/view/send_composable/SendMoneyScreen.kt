package com.example.nexuspay.ui.screens.bottom_nav.home.view.send_composable

import AppTypography
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.nexuspay.R
import com.example.nexuspay.ui.custom_composable.CustomTextField
import com.example.nexuspay.ui.custom_composable.CustomTopAppBar
import com.example.nexuspay.ui.custom_composable.ShimmerBox
import com.example.nexuspay.ui.custom_composable.ShimmerCircle
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.SendViewModel
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SendMoneyScreen(navController: NavController, image: String?) {

    val context = LocalContext.current
    val viewModel = koinViewModel<SendViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(
        Modifier
            .background(Color.Black.copy(0.9f))
            .fillMaxSize()
    ) {
        CustomTopAppBar(
            title = "Send Money",
            navigationIcon = {
                Icon(
                    painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "back arrow",
                    tint = LightBlue,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .width(20.dp)
                        .height(20.dp)
                        .clickable { navController.popBackStack() }
                )
                Spacer(Modifier.width(30.dp))
            },
            action = {
                AsyncImage(
                    model = image ?: "",
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(40.dp)
                        .clip(RoundedCornerShape(50.dp))
                )
            },
        )

        Column(
            Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth(),
        )
        {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomTextField(
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                            tint = LightGray,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Name, email, or phone number",
                            style = AppTypography.bodyMedium.copy(LightGray.copy(0.5f))
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.90f),
                    title = "",
                ) {}
            }


            Spacer(Modifier.height(10.dp))

            Text(
                text = "RECENT CONTACTS",
                style = AppTypography.titleSmall
            )

            Spacer(Modifier.height(8.dp))

            when {
                state.usersLoading -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        repeat(4) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Spacer(Modifier.height(4.dp))
                                ShimmerCircle(70.dp)
                                Spacer(Modifier.height(6.dp))
                                ShimmerBox(Modifier.size(50.dp, 10.dp))
                            }
                        }
                    }
                }

                state.usersError != null -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.usersError?:"No network Connecion",
                            style = AppTypography.bodySmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                state.users.isNotEmpty() -> {
                    Contacts(state.users) {
                        viewModel.onContactSelected(it?.identifier)
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            CustomKeyBoard(
                input = state.inputValue,
                title = state.title?:"",
                onValueChange = {
                    viewModel.onValueChange(it)
                },
                onClick = { symbol -> viewModel.onKeyboardClick(symbol) },
                onRemoveClick = { viewModel.onRemoveClick() },
                onTransferClick = { viewModel.onTransferClick() },
                transferLoading = state.requestLoading
            )
        }
    }
}

