package com.alexereh.cocktailbar.ui.customui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    uri: String?,
    colorFilter: ColorFilter? = null
) {
    if (uri != null) {
        SubcomposeAsyncImage(
            model = uri,
            contentDescription = null,
            modifier = modifier
                .clickable {
                    onClick()
                }
                .fillMaxSize(),
            colorFilter = colorFilter,
            contentScale = ContentScale.Crop
        )
    } else {
        Surface(
            modifier = modifier.clickable {
                onClick()
            }.fillMaxSize(),
            color = Color(0xFFEEEEEE),
        ) {
            Icon(
                imageVector = Icons.Outlined.CameraAlt,
                contentDescription = null,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}