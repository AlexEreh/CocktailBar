package com.alexereh.cocktailbar.ui.mycocktails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexereh.cocktailbar.R
import com.alexereh.cocktailbar.components.mycocktails.FakeMyCocktailsComponent
import com.alexereh.cocktailbar.components.mycocktails.MyCocktailsComponent
import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.ui.customui.CustomFAB
import com.alexereh.cocktailbar.ui.theme.CocktailBarTheme

@Composable
fun MyCocktailsScreen(
    component: MyCocktailsComponent
) {
    val cocktails by component.cocktails.collectAsState()

    MyCocktailsScreen(
        cocktails = cocktails,
        selectCocktail = component::selectCocktail,
        onFabClick = component::createCocktail
    )
}

@Composable
fun MyCocktailsScreen(
    cocktails: List<Cocktail>,
    selectCocktail: (Int) -> Unit,
    onFabClick: () -> Unit
) {
    when(cocktails.isNotEmpty()){
        true -> {
            ItemsContent(
                cocktails = cocktails,
                selectCocktail = selectCocktail,
                onFabClick = onFabClick
            )
        }
        false -> {
            NoItemsContent(
                onFabClick = onFabClick
            )
        }
    }

}

@Composable
fun NoItemsContent(
    onFabClick: () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CustomFAB(
                onClick = onFabClick
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.summer_holidays),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
            )
            Text(
                text = "My cocktails",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun ItemsContent(
    cocktails: List<Cocktail>,
    selectCocktail: (Int) -> Unit,
    onFabClick: () -> Unit
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CustomFAB(onClick = onFabClick)
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.White,
                cutoutShape = CircleShape,
                elevation = 10.dp,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 50.dp,
                            topEnd = 50.dp
                        )
                    )
            ){

            }
        },
        isFloatingActionButtonDocked = true
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "My cocktails",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(cocktails) { cocktail ->
                    CocktailCard(
                        cocktail = cocktail,
                        onClick = {
                            selectCocktail(cocktail.id)
                        }
                    )
                }
            }
        }

    }

}

@Preview
@Composable
fun MyCocktailsPreview() {
    CocktailBarTheme {
        MyCocktailsScreen(component = FakeMyCocktailsComponent())
    }
}