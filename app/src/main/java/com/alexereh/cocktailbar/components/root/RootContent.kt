@file:OptIn(ExperimentalMaterial3Api::class)

package com.alexereh.cocktailbar.components.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexereh.cocktailbar.ui.cocktaildetails.CocktailDetailsScreen
import com.alexereh.cocktailbar.ui.createcocktail.CreateCocktailScreen
import com.alexereh.cocktailbar.ui.mycocktails.MyCocktailsScreen
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        Children(
            stack = component.stack,
            modifier = modifier.padding(paddingValues),
            animation = stackAnimation(fade()),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is RootComponent.Child.MyCocktailsChild -> MyCocktailsScreen(
                    component = child.component
                )

                is RootComponent.Child.CreateCocktailChild -> CreateCocktailScreen(
                    component = child.component
                )

                is RootComponent.Child.CocktailDetailsChild -> CocktailDetailsScreen(
                    component = child.component
                )
            }
        }
    }

}