package com.alexereh.cocktailbar.components.root

import com.alexereh.cocktailbar.components.cocktaildetails.CocktailDetailsComponent
import com.alexereh.cocktailbar.components.createcocktail.CreateCocktailComponent
import com.alexereh.cocktailbar.components.mycocktails.MyCocktailsComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>
    //val currentBottomNavItem: MutableStateFlow<BottomNavItem>
    sealed class Child {
        class CocktailDetailsChild(val component: CocktailDetailsComponent) : Child()
        class CreateCocktailChild(val component: CreateCocktailComponent) : Child()
        class MyCocktailsChild(val component: MyCocktailsComponent) : Child()
    }
}