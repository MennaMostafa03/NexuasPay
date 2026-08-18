package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nexuspay.ui.theme.Black
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title : String,
    action : @Composable (RowScope.() -> Unit)? = null,
    navigationIcon :  @Composable (() -> Unit ),
    isLoading : Boolean? =  false
){
    Surface (
        color = Color.Transparent
    ){
        Column{
            CenterAlignedTopAppBar(
                title = {
                    if (isLoading == true) {
                        ShimmerBox(
                            modifier = Modifier
                                .width(120.dp)
                                .height(18.dp)
                        )
                    } else {
                        Text(
                            text = title,
                            style = AppTypography.displayLarge
                        )
                    }
                },
                actions = action?:{},
                navigationIcon = navigationIcon,
                colors = TopAppBarColors(
                    containerColor = Black.copy(0.5f),
                    navigationIconContentColor = LightBlue,
                    titleContentColor = LightBlue,
                    actionIconContentColor = LightBlue,
                    scrolledContainerColor = Color.Transparent,
                    subtitleContentColor = Color.Transparent
                ),
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = LightGray.copy(alpha = 0.1f)
        )
    }
}