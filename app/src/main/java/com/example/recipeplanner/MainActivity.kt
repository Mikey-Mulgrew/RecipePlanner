package com.example.recipeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.recipeBook.HomeScreen
import com.example.recipeplanner.ui.recipeBook.HomeScreenViewModel
import com.example.recipeplanner.ui.theme.RecipePlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = RecipeDatabase.getInstance(applicationContext)
        val homeViewModel = HomeScreenViewModel(RecipeRepository(RecipeLocalDataSource(database)))

        setContent {
            RecipePlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(homeViewModel)
                }
            }
        }
    }
}
