package com.alexereh.cocktailbar.ui.customui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    outlined: Boolean = false,
    onClick: () -> Unit,
    text: String
) {
    when(outlined) {
        true -> {
            OutlinedButton(
                modifier = modifier,
                onClick = onClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF4B97FF)
                ),
                border = BorderStroke(1.dp, Color(0xFF4B97FF))
            ) {
                Text(
                    text = text,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
        false -> {
            FilledTonalButton(
                onClick = onClick,
                modifier = modifier,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(0xFF4B97FF),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = text,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}