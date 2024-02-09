package com.example.recipeplanner.data

class RecipeRepository(private val recipeLocalDataSource: RecipeLocalDataSource) {

    suspend fun recipes() = recipeLocalDataSource.recipes()

    suspend fun addRecipe(recipe: Recipe) {
        recipeLocalDataSource.insertOrUpdateRecipe(recipe)
    }
}