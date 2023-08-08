package com.alexereh.cocktailbar.ui.cocktaildetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexereh.cocktailbar.components.cocktaildetails.CocktailDetailsComponent
import com.alexereh.cocktailbar.ui.customui.CustomButton
import com.alexereh.cocktailbar.ui.customui.CustomImage

@Composable
fun CocktailDetailsScreen(
    component: CocktailDetailsComponent
) {
    val cocktail by component.cocktail.collectAsState()
    val scrollState = rememberScrollState()
    when (cocktail) {
        null -> {

        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .absolutePadding(top = 0.dp, bottom = 0.dp)
                ) {
                    CustomImage(
                        uri = cocktail!!.imageUri,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cocktail!!.title,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center
                    )
                    cocktail!!.description?.let {
                        Text(
                            text = cocktail!!.description!!,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        cocktail!!.ingredientsList.forEachIndexed { index, str ->
                            Text(
                                text = str,
                                fontSize = 16.sp
                            )
                            if (index != cocktail!!.ingredientsList.lastIndex && cocktail!!.ingredientsList.isNotEmpty()) {
                                Spacer(
                                    modifier = Modifier.height(10.dp)
                                )
                                Text(
                                    text = "―",
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                    cocktail!!.recipe?.let {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Recipe:",
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                            Text(
                                text = cocktail!!.recipe!!,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    CustomButton(
                        onClick = component::edit,
                        text = "Edit",
                        modifier = Modifier.fillMaxWidth()
                    )
                    CustomButton(
                        onClick = component::delete,
                        text = "Delete",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}