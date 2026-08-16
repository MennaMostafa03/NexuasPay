package com.example.nexuspay.ui.screens.bottom_nav.home.view.send_composable

import AppTypography
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexuspay.R
import com.example.nexuspay.ui.theme.LightGray
import com.example.nexuspay.ui.theme.Milky

@Composable
fun KeyButton(
    symbol : Char,
    modifier: Modifier,
    onSymbolClick : (number : Char) -> Unit
){
    Button(
        onClick = {
            onSymbolClick(symbol)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
    ){
        Text(
            text = symbol.toString(),
            style = AppTypography.displayMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun KeyDeleteButton(
    onRemoveClick : () -> Unit,
    modifier : Modifier
){
    Button(
        onClick = {
            onRemoveClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
    ){
        Icon(
            painterResource(R.drawable.ic_remove),
            contentDescription = "remove",
            tint = Milky,
            modifier = Modifier.size(19.dp)
        )
    }
}