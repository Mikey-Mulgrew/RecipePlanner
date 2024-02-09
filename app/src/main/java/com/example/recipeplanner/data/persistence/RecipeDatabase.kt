package com.example.recipeplanner.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipeDataObject::class], version = 1)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO

    companion object {
        private const val databaseFileName = "recipes"

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            synchronized(this){
                INSTANCE?.let { instance ->
                    return instance
                }
                val instance = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    databaseFileName
                ).build()
                INSTANCE =instance
                return instance
            }
        }
    }
}