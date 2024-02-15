package com.example.recipeplanner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeplanner.ui.recipeBook.RecipeBookScreen
import com.example.recipeplanner.ui.recipeBook.RecipeBookViewModel
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeScreen
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeViewModel

enum class Screen {
    RECIPE_BOOK,
    CREATE_RECIPE
}

sealed class NavigationItem(val route: String) {
    object RecipeBook : NavigationItem(Screen.RECIPE_BOOK.name)
    object CreateRecipe : NavigationItem(Screen.CREATE_RECIPE.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    myContext: MyContext,
    navController: NavHostController,
    startDestination: String = NavigationItem.RecipeBook.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.RecipeBook.route) {
            val recipeBookViewModel: RecipeBookViewModel =
                viewModel(factory = myContext.recipeBookViewModelFactory)
            RecipeBookScreen(
                recipeBookViewModel,
                addRecipeClicked = { navController.navigate(NavigationItem.CreateRecipe.route) })
        }
        composable(NavigationItem.CreateRecipe.route) {
            val createRecipeViewModel: CreateRecipeViewModel =
                viewModel(factory = myContext.createRecipeViewModelFactory)
            CreateRecipeScreen(uiState = createRecipeViewModel.uiState.collectAsState().value)
        }
    }
}