package com.example.recipeplanner.data

import com.example.recipeplanner.data.persistence.IngredientDataObject
import com.example.recipeplanner.data.persistence.RecipeDataObject
import com.example.recipeplanner.data.persistence.RecipeDatabase

class RecipeLocalDataSource(private val recipeDatabase: RecipeDatabase) {

    suspend fun recipes(): List<Recipe> {
        return recipeDatabase.recipeDao().getRecipeWithIngredients().map {
            val ingredients = it.ingredients?.map { ingredient ->
                Ingredient(ingredient.ingredient,ingredient.amount,ingredient.unit)
            }
            Recipe(it.title, ingredients)
        }
    }

    suspend fun insertOrUpdateRecipe(recipe: Recipe) {
        recipe.let {
            val ingredientDataObjects = it.ingredients?.map { ingredient ->
                IngredientDataObject(ingredient.name, ingredient.amount, ingredient.unit)
            }
            val adaptedRecipeDataObject = RecipeDataObject(it.title, ingredientDataObjects)
            recipeDatabase.recipeDao().insertRecipe(adaptedRecipeDataObject)
        }
    }
}