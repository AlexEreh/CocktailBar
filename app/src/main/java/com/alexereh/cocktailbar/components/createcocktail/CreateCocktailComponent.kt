package com.alexereh.cocktailbar.components.createcocktail

import android.net.Uri
import kotlinx.coroutines.flow.StateFlow

interface CreateCocktailComponent {
    val ingredients: StateFlow<List<String>>
    val title: StateFlow<String>
    val description: StateFlow<String>
    val recipe: StateFlow<String>
    val uri: StateFlow<String?>

    fun updateTitle(newText: String)
    fun updateDescription(newText: String)
    fun updateRecipe(newText: String)

    fun save()
    fun cancel()
    fun updatePhoto(newUri: Uri?)
    fun addIngredient(value: String)
    fun removeIngredient(value: String)
    //fun updatePhoto()
}