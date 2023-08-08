package com.alexereh.cocktailbar.components.mycocktails

import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.StateFlow

interface MyCocktailsComponent {
    val cocktails: StateFlow<List<Cocktail>>

    fun selectCocktail(cocktail: Int)
    fun createCocktail()
}