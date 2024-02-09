package com.example.recipeplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeScreen
import com.example.recipeplanner.ui.theme.home.HomeScreenViewModel
import com.example.recipeplanner.ui.theme.RecipePlannerTheme
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeActivity
import com.example.recipeplanner.ui.theme.home.HomeActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecipePlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    launchBasicNavigation({launchHome()}, {launchCreateRecipe()})
                }
            }
        }
    }

    private fun launchHome() {
        val intent = Intent(this, HomeActivity::class.java)
        ContextCompat.startActivity(this, intent, null)
    }

    private fun launchCreateRecipe() {
        val intent = Intent(this, CreateRecipeActivity::class.java)
        ContextCompat.startActivity(this, intent, null)
    }
}

@Composable
fun launchBasicNavigation(
    launchHome : () -> Unit,
    launchCreateRecipe: () -> Unit,
){
    Column {
        Button(
            onClick = { launchHome() }
        ) {
            Text(
                text = "Home",
                fontSize = 36.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Button(
            onClick = { launchCreateRecipe() }
        ) {
            Text(
                text = "Create Recipe",
                fontSize = 36.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

