package com.alexereh.cocktailbar.repo

import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
    fun getCocktails(): Flow<List<Cocktail>>
    fun getCocktail(id: Int): Flow<Cocktail>
    fun createEditCocktail(cocktail: Cocktail)
    fun deleteCocktail(id: Int)
}