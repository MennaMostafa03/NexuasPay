package com.example.nexuspay.ui.screens.bottom_nav.card.view

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.nexuspay.ui.theme.Gray
import com.example.nexuspay.ui.theme.LightGray


@Composable
fun CardDetails(
    icon : Painter,
    header : String,
    text : String,
    color: Color
){
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            modifier= Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Gray),
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                icon,
                contentDescription = "money",
                tint = LightGray,
                modifier = Modifier
                    .padding(8.dp)
                    .width(16.dp)
                    .height(20.dp)
            )
        }

        Spacer(Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        )
        {
            Text(header,
                style = AppTypography.bodyMedium
            )

            Text(
                text,
                style = AppTypography.bodySmall.copy(color = color)
            )
        }
    }
}