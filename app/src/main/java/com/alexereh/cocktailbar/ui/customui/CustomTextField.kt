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
    //supportingText: String? = null,
    onValueChange: (String) -> Unit,
    mustNotBeEmpty: Boolean = false
) {
    val errorColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFFFF0000),
        unfocusedBorderColor = Color(0xFFFF0000),
        focusedLabelColor = Color(0xFFFF0000),
        unfocusedLabelColor = Color(0xFFFF0000),
        focusedSupportingTextColor = Color(0xFFFF0000),
        unfocusedSupportingTextColor = Color(0xFFFF0000),
    )
    val optionalColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color(0xFF79747E),
        focusedLabelColor = Color(0xFF79747E),
        focusedSupportingTextColor = Color(0xFF79747E),
        unfocusedBorderColor = Color(0xFF79747E),
        unfocusedLabelColor = Color(0xFF79747E),
        unfocusedSupportingTextColor = Color(0xFF79747E),
    )
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
            when (mustNotBeEmpty) {
                true -> {
                    if (value == "") {
                        Text(text = "Add title")
                    }
                }

                false -> {
                    Text("Optional field")
                }
            }
        },
        onValueChange = onValueChange,
        colors =
            if (value == "") {
                if (mustNotBeEmpty) {
                    errorColors
                } else{
                    optionalColors
                }
            } else {
                optionalColors
            },
        shape = RoundedCornerShape(34.dp)
    )
}