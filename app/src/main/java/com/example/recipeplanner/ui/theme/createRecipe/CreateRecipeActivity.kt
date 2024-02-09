package com.example.recipeplanner.ui.theme.createRecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.theme.RecipePlannerTheme


class CreateRecipeActivity : ComponentActivity() {

//    private val createRecipeVM: CreateRecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val database = RecipeDatabase.getInstance(applicationContext)
        val createRecipeVM = CreateRecipeViewModel(RecipeRepository(RecipeLocalDataSource(database)))
        super.onCreate(savedInstanceState)
    //        val onBackPressed = { onBackPressedDispatcher.onBackPressed() }
    //        val finishActivity = { finish() }

            setContent {
                val uiState = createRecipeVM.uiState.collectAsStateWithLifecycle().value
                RecipePlannerTheme {
                    CreateRecipeScreen( uiState )
                }
            }
    }
}