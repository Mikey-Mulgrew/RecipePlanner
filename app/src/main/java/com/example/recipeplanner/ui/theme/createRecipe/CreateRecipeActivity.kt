package com.example.recipeplanner.ui.theme.createRecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.theme.RecipePlannerTheme


class CreateRecipeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val database = RecipeDatabase.getInstance(applicationContext)
        val createRecipeVM = CreateRecipeViewModel(RecipeRepository(RecipeLocalDataSource(database)))
        super.onCreate(savedInstanceState)
    //        val onBackPressed = { onBackPressedDispatcher.onBackPressed() }
    //        val finishActivity = { finish() }

            setContent {
                val uiState = createRecipeVM.uiState.collectAsState().value
                RecipePlannerTheme {
                    CreateRecipeScreen( uiState )
                }
            }
    }
}