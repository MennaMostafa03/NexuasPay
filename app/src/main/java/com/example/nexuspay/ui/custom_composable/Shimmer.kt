package com.example.nexuspay.ui.custom_composable

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.shimmer(): Modifier = composed {

    val transition = rememberInfiniteTransition(label = "Shimmer")

    val translateAnimation = transition.animateFloat(
        initialValue = -300f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerAnimation"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2D2D2D),
            Color(0xFF4A4A4A),
            Color(0xFF2D2D2D)
        ),
        start = Offset(
            translateAnimation.value,
            translateAnimation.value
        ),
        end = Offset(
            translateAnimation.value + 250f,
            translateAnimation.value + 250f
        )
    )
    background(brush)
}

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .shimmer()
    )
}

@Composable
fun ShimmerCircle(
    size: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .shimmer()
    )
}