package com.alexereh.cocktailbar.ui.customui

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    minHeight: Dp = Dp.Unspecified,
    label: String,
    value: String,
    singleLine: Boolean = false,
    supportingText: String? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.sizeIn(minHeight = minHeight),
        label = {
            Text(
                modifier = Modifier,
                text = label
            )
        },
        singleLine = singleLine,
        value = value,
        supportingText = {
            supportingText?.let {
                Text(supportingText)
            }
        },
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF79747E),
            focusedLabelColor = Color(0xFF79747E),
            focusedSupportingTextColor = Color(0xFF79747E),
        ),
        shape = RoundedCornerShape(34.dp)
    )
}