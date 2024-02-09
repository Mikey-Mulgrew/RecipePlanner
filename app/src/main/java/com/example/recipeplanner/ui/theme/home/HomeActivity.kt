package com.example.recipeplanner.ui.theme.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.theme.RecipePlannerTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = RecipeDatabase.getInstance(applicationContext)
        val viewModel = HomeScreenViewModel(RecipeRepository(RecipeLocalDataSource(database)))
        setContent {
            RecipePlannerTheme {
                val uiState = viewModel.uiState.collectAsState().value
                RecipeTest(uiState = uiState, getRecipes = { viewModel.getRecipes() }, addRecipe = {viewModel.addRecipe(it)})
            }
        }
    }
}