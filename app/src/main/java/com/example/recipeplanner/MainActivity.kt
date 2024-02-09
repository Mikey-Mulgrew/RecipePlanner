package com.example.recipeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeplanner.ui.theme.CreateRecipePage
import androidx.compose.ui.Modifier
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.RecipeTest
import com.example.recipeplanner.ui.TestScreenViewModel
import com.example.recipeplanner.ui.theme.RecipePlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipePlannerTheme {
                // A surface container using the 'background' color from the theme
                CreateRecipePage()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ){}
            }
        }
    }
}
