package com.example.nexuspay.ui.custom_composable

import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexuspay.ui.theme.DarkGray
import com.example.nexuspay.ui.theme.LightGray

@Composable
fun CustomTextField(
    leadingIcon : (@Composable ()-> Unit)? = null,
    placeholder : (@Composable ()-> Unit)? = {},
    modifier: Modifier,
    title : String?,
    label : String? = null,
    textColor: Color,
    visualTransformation: VisualTransformation? = VisualTransformation.None,
    onValueChange : (String) -> Unit,
){
        label?.let {
            Text(
                label,
                style = AppTypography.titleSmall)
            Spacer(Modifier.height(12.dp))
        }

    if (visualTransformation != null) {
        TextField(
            value = title?:"",
            onValueChange = onValueChange,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            modifier = modifier,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = DarkGray,
                cursorColor = LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = textColor
            ),
            visualTransformation = visualTransformation
        )
    }
    }
