package com.alexereh.cocktailbar.components.createcocktail

import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeCreateCocktailComponent: CreateCocktailComponent {
    override val ingredients: StateFlow<List<String>>
        get() = MutableStateFlow(listOf("9 cups sprite", "small bunch mint"))
    override val title: StateFlow<String>
        get() = MutableStateFlow("Sample Cocktail")
    override val description: StateFlow<String>
        get() = MutableStateFlow("To make this homemade")
    override val recipe: StateFlow<String>
        get() = MutableStateFlow("Simply combine all the ingredients")
    override val uri: StateFlow<String?>
        get() = MutableStateFlow<String?>(null)

    override fun updateTitle(newText: String) {
    }

    override fun updateDescription(newText: String) {
    }

    override fun updateRecipe(newText: String) {
    }

    override fun save() {
    }

    override fun cancel() {
    }

    override fun updatePhoto(newUri: Uri?) {
    }

    override fun addIngredient(value: String) {
    }

    override fun removeIngredient(value: String) {
    }
}