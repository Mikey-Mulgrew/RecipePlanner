package com.example.recipeplanner.data

data class Recipe(
    val title: String,
    val ingredients: List<Ingredient>?
)

enum class IngredientUnits(value: String) {
    MIL("ml"),
    GRAM("g"),
}

data class Ingredient(
    val ingredient: String,
    val amount: Float,
    val unit: IngredientUnits
)