package com.example.recipeplanner.ui.theme.createRecipe

import com.example.recipeplanner.data.Ingredient

data class CreateRecipeUIState(
    val title: String = "",
    val nextIngredient: Ingredient = Ingredient("", 0f),
    val ingredients: List<Ingredient> = emptyList(),
    val nextInstruction: String = "",
    val instructions: List<String> = emptyList(),
    val sendAction: (RecipeAction) -> Unit = {}
)

sealed class CreateRecipeState {
    data object Editing : CreateRecipeState()
    data object Saved : CreateRecipeState()
}

sealed class RecipeAction{
    data class UpdateRecipeTitle(val title: String) : RecipeAction()
    data class UpdateIngredient(val ingredient: Ingredient) : RecipeAction()
    data class AddIngredient(val ingredient: Ingredient) : RecipeAction()
    data class RemoveIngredient(val ingredient: Ingredient) : RecipeAction()
    data class AddInstruction(val instruction: String) : RecipeAction()
    data class RemoveInstruction(val instruction: String) : RecipeAction()
    data object SaveRecipe: RecipeAction()
}
