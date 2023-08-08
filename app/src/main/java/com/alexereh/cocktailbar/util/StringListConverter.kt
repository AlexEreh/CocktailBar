package com.alexereh.cocktailbar.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StringListConverter: KoinComponent {
    private val gson: Gson by inject()

    @TypeConverter
    fun stringToObject(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<String>): String {
        return gson.toJson(list)
    }
}