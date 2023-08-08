package com.alexereh.cocktailbar.components.mycocktails

import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.repo.CocktailRepository
import com.alexereh.cocktailbar.util.CoroutineRetainedInstance
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnResume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RealMyCocktailsComponent(
    componentContext: ComponentContext,
    private val navigateToDetails: (Int) -> Unit,
    private val navigateToCreateCocktail: () -> Unit,
): MyCocktailsComponent, KoinComponent, ComponentContext by componentContext {
    private val coroutineRetainedInstance = instanceKeeper.getOrCreate { CoroutineRetainedInstance(Dispatchers.IO) }

    private val repo: CocktailRepository by inject()

    private val _cocktails = MutableStateFlow(emptyList<Cocktail>())
    override val cocktails: StateFlow<List<Cocktail>>
        get() = _cocktails

    override fun selectCocktail(cocktail: Int) {
        navigateToDetails(cocktail)
    }

    override fun createCocktail() {
        navigateToCreateCocktail()
    }

    init {
        lifecycle.doOnResume {
            coroutineRetainedInstance.launch {
                val defaultCocktails = repo.getCocktails()
                defaultCocktails.collect {
                    _cocktails.emit(it)
                }
            }
        }
    }
}