package com.alexereh.cocktailbar.components.cocktaildetails

import com.alexereh.cocktailbar.components.mycocktails.CoroutineRetainedInstance
import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.repo.CocktailRepository
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RealCocktailDetailsComponent(
    val cocktailId: Int,
    private val navigateToEdit: (Int) -> Unit,
    private val navigateBack: () -> Unit,
    componentContext: ComponentContext
): CocktailDetailsComponent, KoinComponent,
    ComponentContext by componentContext {

    private val coroutineRetainedInstance = instanceKeeper.getOrCreate { CoroutineRetainedInstance(Dispatchers.IO) }
    private val repo: CocktailRepository by inject()

    private val _cocktail = MutableStateFlow<Cocktail?>(null)
    override val cocktail: StateFlow<Cocktail?>
        get() = _cocktail

    override fun edit() {
        navigateToEdit(cocktailId)
    }

    override fun delete() {
        coroutineRetainedInstance.launch {
            repo.deleteCocktail(cocktailId)
        }
        navigateBack()
    }

    init {
        lifecycle.doOnCreate {
            val cocktailFromRepo = repo.getCocktail(cocktailId)
            coroutineRetainedInstance.launch {
                cocktailFromRepo.collect {
                    _cocktail.emit(it)
                }
            }
        }
    }
}