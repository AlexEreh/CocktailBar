package com.alexereh.cocktailbar.di

import androidx.room.Room
import com.alexereh.cocktailbar.db.CocktailDatabase
import com.alexereh.cocktailbar.repo.CocktailRepository
import com.alexereh.cocktailbar.repo.CocktailRepositoryImpl
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CocktailDatabase::class.java,
            "cocktails"
        ).build()
    }

    single<CocktailRepository> {
        CocktailRepositoryImpl(get())
    }

    single {
        Gson()
    }
}