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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.IngredientUnits
import com.example.recipeplanner.data.Recipe
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = viewModel.uiState.collectAsState().value
                    RecipeTest(uiState = uiState,
                        getRecipes = { viewModel.getRecipes() },
                        addRecipe = { viewModel.addRecipe(it) })
                }
            }
        }
    }
}

@Composable
fun RecipeTest(uiState: RecipeUiState, getRecipes: () -> Unit, addRecipe: (Recipe) -> Unit) {

    Column {

        Recipes(uiState, getRecipes)
        RecipeForm(addRecipe)
    }
}

@Composable
fun Recipes(uiState: RecipeUiState, getRecipes: () -> Unit) {
    Button(onClick = { getRecipes() }) {
        Text(text = "getRecipes!")
    }
    when (uiState) {
        is RecipeUiState.Loaded -> {
            LazyColumn {
                items(uiState.recipes) {
                    Box(modifier = Modifier.border(1.dp, Color.Black)) { Column{
                        Text(text = it.title)
                        Text(text = it.ingredients?.get(0).toString())
                        } }
                }
            }
        }

        RecipeUiState.Empty -> {
            Text("nothing!")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeForm(addRecipe: (Recipe) -> Unit) {
    Column {
        val ingredientForm = remember {
            mutableStateOf(Ingredient("", 0f, IngredientUnits.GRAM))
        }
        var recipeName by remember { mutableStateOf("") }

        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            modifier = Modifier.fillMaxWidth()
        )

        var ingredientName by remember { mutableStateOf("hi") }
        TextField(
            value = ingredientName,
            onValueChange = { ingredientName = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                ingredientForm.value = ingredientForm.value.copy(ingredient = ingredientName)
            }),
            modifier = Modifier.fillMaxWidth()
        )

        var amount by remember { mutableStateOf("") }
        TextField(
            value = amount,
            onValueChange = { amount = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                ingredientForm.value = ingredientForm.value.copy(amount = amount.toFloat())
            }),
            modifier = Modifier.fillMaxWidth()
        )

        var expanded by remember { mutableStateOf(true) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { IngredientUnits.GRAM.name },
                onClick = {
                    ingredientForm.value = ingredientForm.value.copy(unit = IngredientUnits.GRAM)
                })
            DropdownMenuItem(
                text = { IngredientUnits.MIL.name },
                onClick = {
                    ingredientForm.value = ingredientForm.value.copy(unit = IngredientUnits.GRAM)
                })
        }

        Button(
            onClick = {
                val recipe = Recipe(recipeName, listOf(ingredientForm.value))
                addRecipe(recipe) },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text("submit recipe ")
        }
    }
}