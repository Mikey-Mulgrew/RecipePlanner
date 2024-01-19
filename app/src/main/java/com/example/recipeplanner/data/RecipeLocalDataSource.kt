package com.example.recipeplanner.data

import com.example.recipeplanner.data.persistence.RecipeDataObject
import com.example.recipeplanner.data.persistence.RecipeDatabase

class RecipeLocalDataSource(private val recipeDatabase: RecipeDatabase) {

    suspend fun recipes() = recipeDatabase.recipeDao().getAll()

    suspend fun insertOrUpdateRecipe(recipe: Recipe) {
        val adaptedRecipeDataObject = recipe.let {
            RecipeDataObject(title = recipe.title)
        }

        recipeDatabase.recipeDao().insertRecipe(adaptedRecipeDataObject)
    }
}