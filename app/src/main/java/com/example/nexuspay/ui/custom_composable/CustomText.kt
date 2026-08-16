package com.example.nexuspay.ui.custom_composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomText(
    text: String,
    currencyStyle: TextStyle,
    numberStyle: TextStyle,
    decimalStyle: TextStyle,
    isLoading : Boolean? =  false
){
    val currency = text.takeWhile { !it.isDigit() }
    val number = text.drop(currency.length).substringBefore('.')
    val decimal = if (text.contains('.')) "." + text.substringAfter('.') else ""

    if (isLoading == true) {
        ShimmerBox(
            modifier = Modifier
                .width(170.dp)
                .height(38.dp)
        )
    } else {
        Text(
            text = buildAnnotatedString {
                withStyle(currencyStyle.toSpanStyle()) {
                    append(currency)
                }

                withStyle(numberStyle.toSpanStyle()) {
                    append(number)
                }

                withStyle(decimalStyle.toSpanStyle()) {
                    append(decimal)
                }
            }
        )
    }
}