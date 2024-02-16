package com.example.recipeplanner.data.persistence

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object InstructionsConverter {
    @TypeConverter
    fun convertToString(instructions: List<String>?): String {
        return Json.encodeToString(instructions)
    }

    @TypeConverter
    fun convertToList(json: String): List<String>? {
        return Json.decodeFromString<List<String>>(json)
    }
}