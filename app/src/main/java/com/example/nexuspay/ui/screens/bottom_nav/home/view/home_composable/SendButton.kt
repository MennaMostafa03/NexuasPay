package com.example.nexuspay.ui.screens.bottom_nav.home.view.home_composable

import AppTypography
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nexuspay.R
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.LightBlue
import com.example.nexuspay.ui.theme.Milky

@Composable
fun SendButton(sendClick : () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = DarkGray.copy(0.4f),
        modifier = Modifier
            .width(160.dp)
            .border(1.dp, LightBlue.copy(0.2f), RoundedCornerShape(40.dp))
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .clickable{
                    sendClick()
                }
        ){
            Icon(
                painterResource(R.drawable.ic_send),
                contentDescription = "send",
                tint = LightBlue,
                modifier = Modifier.width(18.dp).height(16.dp)
            )

            Spacer(Modifier.width(8.dp))

            Text("Send", style = AppTypography.titleSmall.copy(Milky))
        }

    }
}