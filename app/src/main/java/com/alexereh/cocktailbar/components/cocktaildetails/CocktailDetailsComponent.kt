package com.alexereh.cocktailbar.components.cocktaildetails

import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.StateFlow

interface CocktailDetailsComponent {
    val cocktail: StateFlow<Cocktail?>
    fun edit()
    fun delete()
}