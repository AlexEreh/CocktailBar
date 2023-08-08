package com.alexereh.cocktailbar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexereh.cocktailbar.db.dao.CocktailDAO
import com.alexereh.cocktailbar.db.entities.DBCocktail

@Database(entities = [DBCocktail::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract val cocktailDAO: CocktailDAO
}