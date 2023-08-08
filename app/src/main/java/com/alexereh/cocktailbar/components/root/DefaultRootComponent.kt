package com.alexereh.cocktailbar.components.root

import android.util.Log
import com.alexereh.cocktailbar.components.cocktaildetails.CocktailDetailsComponent
import com.alexereh.cocktailbar.components.cocktaildetails.RealCocktailDetailsComponent
import com.alexereh.cocktailbar.components.createcocktail.CreateCocktailComponent
import com.alexereh.cocktailbar.components.createcocktail.RealCreateCocktailComponent
import com.alexereh.cocktailbar.components.mycocktails.MyCocktailsComponent
import com.alexereh.cocktailbar.components.mycocktails.RealMyCocktailsComponent
import com.alexereh.cocktailbar.components.root.RootComponent.Child.CocktailDetailsChild
import com.alexereh.cocktailbar.components.root.RootComponent.Child.CreateCocktailChild
import com.alexereh.cocktailbar.components.root.RootComponent.Child.MyCocktailsChild
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    private val _stack: Value<ChildStack<Config, RootComponent.Child>>
        get() = childStack(
            source = navigation,
            initialConfiguration = Config.MyCocktails,
            handleBackButton = true,
            childFactory = ::child,
        )

    override val stack: Value<ChildStack<*, RootComponent.Child>> = _stack

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.CocktailDetails -> CocktailDetailsChild(
                cocktailDetailsComponent(componentContext, config)
            )

            is Config.CreateCocktail -> CreateCocktailChild(
                createCocktailComponent(componentContext, config)
            )

            is Config.MyCocktails -> MyCocktailsChild(
                myCocktailsComponent(componentContext)
            )
        }
    }

    private fun cocktailDetailsComponent(
        componentContext: ComponentContext,
        config: Config.CocktailDetails
    ): CocktailDetailsComponent {
        return RealCocktailDetailsComponent(
            componentContext = componentContext,
            cocktailId = config.cocktailId,
            navigateToEdit = {
                //navigation.pop()
                Log.d("Root","$it")
                val idTemp = it
                navigation.replaceCurrent(Config.CreateCocktail(idTemp))
            },
            navigateBack = {
                navigation.pop()
            },
        )
    }

    private fun createCocktailComponent(componentContext: ComponentContext, config: Config.CreateCocktail): CreateCocktailComponent {
        return RealCreateCocktailComponent(
            navigateBack = {
                navigation.pop()
            },
            cocktailId = config.cocktailId,
            componentContext = componentContext
        )
    }

    private fun myCocktailsComponent(componentContext: ComponentContext): MyCocktailsComponent {
        return RealMyCocktailsComponent(
            componentContext = componentContext,
            navigateToDetails = { cocktail ->
                navigation.push(Config.CocktailDetails(cocktail))
            },
            navigateToCreateCocktail = {
                navigation.push(Config.CreateCocktail(0))
            }
        )
    }
}