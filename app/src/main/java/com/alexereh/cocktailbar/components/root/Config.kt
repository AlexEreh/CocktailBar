package com.alexereh.cocktailbar.components.root

import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface Config : Parcelable {
    @Parcelize
    data class CocktailDetails(
        val cocktailId: Int
    ) : Config

    @Parcelize
    data class CreateCocktail(
        val cocktailId: Int
    ) : Config

    @Parcelize
    data object MyCocktails : Config
}