package com.example.nexuspay

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.nexuspay.ui.routes.Routes
import com.example.nexuspay.ui.screens.bottom_nav.BottomNavigation
import com.example.nexuspay.ui.screens.bottom_nav.card.CardScreen
import com.example.nexuspay.ui.screens.bottom_nav.home.view.home_composable.HomeScreen
import com.example.nexuspay.ui.screens.bottom_nav.home.view.send_composable.SendMoneyScreen
import com.example.nexuspay.ui.screens.bottom_nav.transaction.TransactionScreen
import com.example.nexuspay.ui.theme.NexusPayTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
        )
        setContent {
            NexusPayTheme {
                NexusPay()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NexusPay(){

    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) {
        NavHost(
            navController,
            startDestination = Routes.HomeRoute,
        ) {

            composable<Routes.HomeRoute>{
                HomeScreen(navController)
            }

            composable<Routes.SendMoneyRoute>{ navBackStackEntry ->
                val route = navBackStackEntry.toRoute<Routes.SendMoneyRoute>()
                SendMoneyScreen(navController, route.image)
            }

            composable<Routes.CardRoute>{
                CardScreen(navController)
            }
            composable<Routes.TransactionRoute>{
                TransactionScreen(navController)
            }
        }
    }
}
