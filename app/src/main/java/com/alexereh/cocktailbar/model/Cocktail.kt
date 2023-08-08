package com.alexereh.cocktailbar.model

data class Cocktail(
    val id: Int,
    val title: String,
    val description: String?,
    val recipe: String?,
    val imageUri: String?,
    val ingredientsList: List<String>
)
