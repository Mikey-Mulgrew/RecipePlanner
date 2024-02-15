package com.example.recipeplanner

enum class Screen {
    RECIPE_BOOK,
    CREATE_RECIPE
}
sealed class NavigationItem(val route: String) {
    object RecipeBook : NavigationItem(Screen.RECIPE_BOOK.name)
    object CreateRecipe : NavigationItem(Screen.CREATE_RECIPE.name)
}