package com.example.recipeplanner.data.persistence

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object IngredientsConverter {
    @TypeConverter
    fun convertToJsonString(ingredient: List<IngredientDataObject>?):String {
        return Json.encodeToString(ingredient)
    }

    @TypeConverter
    fun convertToObject(json: String): List<IngredientDataObject>? {
        return Json.decodeFromString<List<IngredientDataObject>>(json)
    }
}

