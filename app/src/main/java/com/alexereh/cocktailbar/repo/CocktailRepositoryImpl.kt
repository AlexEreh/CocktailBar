package com.alexereh.cocktailbar.repo

import com.alexereh.cocktailbar.db.CocktailDatabase
import com.alexereh.cocktailbar.db.entities.DBCocktail
import com.alexereh.cocktailbar.model.Cocktail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CocktailRepositoryImpl(
    private val database: CocktailDatabase
): CocktailRepository {
    override fun getCocktails(): Flow<List<Cocktail>> {
        return flow {
            database.cocktailDAO.getCocktails().map { list ->
                list.map {
                    it.asDomainModel()
                }
            }.collect {
                emit(it)
            }
        }
    }

    override fun getCocktail(id: Int): Flow<Cocktail> {
        return flow {
            database.cocktailDAO.getCocktail(id).map {
                it.asDomainModel()
            }.collect {
                emit(it)
            }
        }
    }

    override fun createEditCocktail(cocktail: Cocktail) {
        database.cocktailDAO.insertCocktail(DBCocktail.fromDomainModel(cocktail))
    }

    override fun deleteCocktail(id: Int) {
        database.cocktailDAO.deleteCocktail(id)
    }
}