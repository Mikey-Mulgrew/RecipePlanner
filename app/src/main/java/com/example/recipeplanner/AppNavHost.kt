package com.example.recipeplanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeplanner.ui.recipeBook.RecipeBookScreen
import com.example.recipeplanner.ui.recipeBook.RecipeBookViewModel

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
            val recipeBookViewModel: RecipeBookViewModel = viewModel(factory = myContext.recipeBookViewModelFactory)
            RecipeBookScreen(recipeBookViewModel) }
        composable(NavigationItem.CreateRecipe.route) {
        }
    }
}