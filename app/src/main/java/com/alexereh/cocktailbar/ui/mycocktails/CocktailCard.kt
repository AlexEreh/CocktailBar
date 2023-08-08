package com.alexereh.cocktailbar.ui.mycocktails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.ui.customui.CustomImage

@Composable
fun CocktailCard(
    modifier: Modifier = Modifier,
    onClick: (Cocktail) -> Unit,
    cocktail: Cocktail
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(60.dp))
            .background(Color.Gray),
    ) {
        CustomImage(
            onClick = {
                onClick(cocktail)
            },
            uri = cocktail.imageUri,
            colorFilter = ColorFilter.tint(Color.DarkGray.copy(0.1f), BlendMode.Darken)
        )
        Text(
            text = cocktail.title,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            color = Color.White,
            fontSize = 17.sp
        )
    }
}