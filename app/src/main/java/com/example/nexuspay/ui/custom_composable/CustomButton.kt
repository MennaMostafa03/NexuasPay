package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nexuspay.ui.theme.Navy

@Composable
fun CustomButton(
    text: String,
    icon: Painter? = null,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    containerColor: Color,     // optional (عشان تتحكم في لون الخلفية)
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = AppTypography.displayLarge.copy(color = Navy)
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