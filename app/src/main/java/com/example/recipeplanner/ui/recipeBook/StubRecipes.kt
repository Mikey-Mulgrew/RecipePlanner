package com.example.recipeplanner.ui.recipeBook

import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.IngredientUnits
import com.example.recipeplanner.data.Recipe

object StubRecipes {
    private fun ingredients(): List<Ingredient> = listOf(
        Ingredient("ingredient1", 1f, IngredientUnits.GRAM),
        Ingredient("ingredient2", 2f, IngredientUnits.MIL)
    )

    private fun instructions(): List<String> = listOf(
        "instruction1", "instruction2"
    )

    fun getStaticRecipes(): List<Recipe> = listOf(
        Recipe(id = 0, title = "test1", ingredients = ingredients(), instructions = instructions()),
        Recipe(id = 0, title = "test2", ingredients = ingredients(), instructions = instructions())
    )
}