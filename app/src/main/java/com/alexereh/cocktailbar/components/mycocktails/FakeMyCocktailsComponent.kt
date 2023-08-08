package com.alexereh.cocktailbar.components.mycocktails

import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeMyCocktailsComponent: MyCocktailsComponent {
    override val cocktails: StateFlow<List<Cocktail>>
        get() = MutableStateFlow(
            List(10) {
                Cocktail(it, "Sample Cocktail", "", "", null, emptyList())
            }
        )

    override fun selectCocktail(cocktail: Int) {
    }

    override fun createCocktail() {
    }
}