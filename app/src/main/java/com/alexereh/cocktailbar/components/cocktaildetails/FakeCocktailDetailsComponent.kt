package com.alexereh.cocktailbar.components.cocktaildetails

import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.StateFlow

class FakeCocktailDetailsComponent: CocktailDetailsComponent {
    override val cocktail: StateFlow<Cocktail>
        get() = TODO("Not yet implemented")

    override fun edit() {
    }

    override fun delete() {
    }
}