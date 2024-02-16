package com.example.recipeplanner.data

data class Recipe(
    val id: Int,
    val title: String,
    val ingredients: List<Ingredient>?,
    val instructions: List<String>?
)

enum class IngredientUnits(value: String) {
    MIL("ml"),
    GRAM("g"),
}

data class Ingredient(
    val name: String,
    val amount: Float,
    val unit: IngredientUnits = IngredientUnits.GRAM
)