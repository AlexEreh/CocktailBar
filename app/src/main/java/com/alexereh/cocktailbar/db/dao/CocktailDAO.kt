package com.alexereh.cocktailbar.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexereh.cocktailbar.db.entities.DBCocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDAO {
    @Query("SELECT * FROM Cocktail WHERE id = :id")
    fun getCocktail(
        id: Int
    ): Flow<DBCocktail>

    @Query("SELECT * FROM Cocktail")
    fun getCocktails(
    ): Flow<List<DBCocktail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktail: DBCocktail)

    @Query("DELETE FROM Cocktail WHERE id=:id")
    fun deleteCocktail(id: Int)
}