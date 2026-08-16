package com.example.nexuspay.ui.screens.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.nexuspay.R
import com.example.nexuspay.ui.routes.Routes
import com.example.nexuspay.ui.theme.Black
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigation(navController: NavHostController) {

    var selectedIndex by remember { mutableIntStateOf(0) }

    val navItem = listOf(
        NavItemData(R.drawable.ic_home, "Home", 0, Routes.HomeRoute ),
        NavItemData(R.drawable.ic_cards, "Cards", 1, Routes.CardRoute),
        NavItemData(R.drawable.ic_transaction, "Transaction", 2, Routes.TransactionRoute)
    )

    val navBarItemColor = NavigationBarItemColors(
        selectedIconColor = LightBlue,
        unselectedIconColor = Color.White,
        selectedIndicatorColor = Color.Transparent,
        unselectedTextColor = LightGray,
        disabledIconColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
        selectedTextColor = LightBlue
    )

    Surface(
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        color = Color.Transparent,
    )
    {
        NavigationBar(
            containerColor = Black.copy(0.5f),
            modifier = Modifier
                .height(84.dp)
                .padding(top = 1.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            for (item in navItem){
                val isSelected = selectedIndex == item.index
                NavigationBarItem(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .background(
                            color = if (isSelected) LightBlue.copy(0.1f) else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                    ,
                    selected = isSelected,
                    onClick = {
                        selectedIndex = item.index
                        navController.navigate(item.route)
                    },
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                painter = painterResource(item.icon) ,
                                contentDescription = item.title,
                                Modifier.width(20.dp).height(16.dp))
                        }
                    },
                    label = {
                        Text(item.title, fontSize = 12.sp,
                            color = if (isSelected) LightBlue else Color.White,)
                    },
                    colors = navBarItemColor
                )
            }
        }
    }
}