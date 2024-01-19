package com.example.recipeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.recipeplanner.data.RecipeLocalDataSource
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.persistence.RecipeDatabase
import com.example.recipeplanner.ui.theme.HomeScreenViewModel
import com.example.recipeplanner.ui.theme.RecipePlannerTheme
import com.example.recipeplanner.ui.theme.RecipeUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = RecipeDatabase.getInstance(applicationContext)
        val viewModel = HomeScreenViewModel(RecipeRepository(RecipeLocalDataSource(database)))

        setContent {
            RecipePlannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = viewModel.uiState.collectAsState().value
                    RecipeTest(uiState = uiState, getRecipes = { viewModel.getRecipes() }, addRecipe = {viewModel.addRecipe(it)})
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTest(uiState: RecipeUiState, getRecipes: () -> Unit, addRecipe: (String) -> Unit) {
    var value by remember {
        mutableStateOf("hi")
    }

    Column {
        TextField(
            value = value,
            onValueChange = { value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { addRecipe(value) }),
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { getRecipes() }, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
            Text("Get Recipes!")
        }
        Recipes(uiState)
    }
}

@Composable
fun Recipes(uiState: RecipeUiState) {
    when (uiState) {
        is RecipeUiState.Loaded -> {
            LazyColumn {
                items(uiState.recipes) {
                    Box(modifier = Modifier.border(1.dp, Color.Black)){ Text(text = it.title) }
                }
            }
        }

        RecipeUiState.Empty -> {
            Text("nothing!")
        }
    }
}