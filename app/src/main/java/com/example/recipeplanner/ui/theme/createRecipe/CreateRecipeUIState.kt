package com.example.recipeplanner.ui.theme.createRecipe

data class CreateRecipeUIState(
    val title: String = "",
    val nextIngredient: IngredientItem = IngredientItem("", 0f),
    val ingredients: List<IngredientItem> = emptyList(),
    val nextInstruction: String = "",
    val instructions: List<String> = emptyList(),
    val sendAction: (RecipeAction) -> Unit = {}
)

data class IngredientItem(
    val name: String,
    val amount: Float,
//    val unit: IngredientUnits
)

enum class IngredientUnits(value: String) {
    MIL("ml"),
    GRAM("g"),
}

sealed class CreateRecipeState {
    data object Editing : CreateRecipeState()
    data object Saved : CreateRecipeState()
}

sealed class RecipeAction{
    data class UpdateRecipeTitle(val title: String) : RecipeAction()
    data class UpdateIngredient(val ingredient: IngredientItem) : RecipeAction()
    data class AddIngredient(val ingredient: IngredientItem) : RecipeAction()
    data class RemoveIngredient(val ingredient: IngredientItem) : RecipeAction()
    data class AddInstruction(val instruction: String) : RecipeAction()
    data class RemoveInstruction(val instruction: String) : RecipeAction()
    data object SaveRecipe: RecipeAction()
}
