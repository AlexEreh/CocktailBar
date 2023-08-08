package com.alexereh.cocktailbar.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.alexereh.cocktailbar.model.Cocktail
import com.alexereh.cocktailbar.util.StringListConverter
import okhttp3.internal.toImmutableList

@Entity(
    tableName = "Cocktail",
    indices = [Index(value = ["title"])]
)
@TypeConverters(StringListConverter::class)
data class DBCocktail(
    @ColumnInfo("id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("recipe") val recipe: String?,
    @ColumnInfo("image_uri") val imageUri: String?,
    @ColumnInfo("ingredientsList") val ingredientsList: List<String>
) {
    fun asDomainModel(): Cocktail {
        return Cocktail(id, title, description, recipe, imageUri, ingredientsList)
    }
    companion object {
        fun fromDomainModel(
            cocktail: Cocktail
        ): DBCocktail {
            return DBCocktail(
                id = cocktail.id,
                title = cocktail.title,
                description = cocktail.description,
                recipe = cocktail.recipe,
                imageUri = cocktail.imageUri,
                ingredientsList = cocktail.ingredientsList.toImmutableList()
            )
        }
    }
}

