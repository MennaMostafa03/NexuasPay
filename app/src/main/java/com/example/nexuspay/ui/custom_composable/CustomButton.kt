package com.example.nexuspay.ui.custom_composable

import AppTypography
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nexuspay.ui.theme.Navy


@Composable
fun CustomButton(
    text : String,
    icon: Painter? = null,
    modifier: Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable{
                onClick()
            }
    ){

        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = AppTypography.displayLarge.copy(
                    Navy,
                    fontWeight = FontWeight.Normal
                ),
            )

            if (icon != null) {
                Spacer(Modifier.width(10.dp))
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Navy,
                    modifier = Modifier.size(19.dp)
                )
            }
        }
    }
}