package com.example.recipeplanner.data

import android.content.Context
import com.example.recipeplanner.data.persistence.RecipeDatabase

class RecipeRepository(private val recipeLocalDataSource: RecipeLocalDataSource) {

    suspend fun recipes() = recipeLocalDataSource.recipes()

    suspend fun addRecipe(recipe: Recipe) {
        recipeLocalDataSource.insertOrUpdateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipeId: Int) {
        recipeLocalDataSource.deleteById(recipeId)
    }
}

fun recipeRepository(applicationContext: Context) : RecipeRepository {
    val database = RecipeDatabase.getInstance(applicationContext)
    return RecipeRepository(RecipeLocalDataSource(database))
}