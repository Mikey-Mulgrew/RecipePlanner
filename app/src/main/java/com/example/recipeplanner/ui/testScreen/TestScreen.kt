package com.example.recipeplanner.ui.testScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.IngredientUnits
import com.example.recipeplanner.data.Recipe


@Composable
fun RecipeTest(viewModel: TestScreenViewModel) {
    val uiState = viewModel.uiState.collectAsState().value
    Column {
        Recipes(uiState) { viewModel.getRecipes() }
        RecipeForm { viewModel.addRecipe(it) }
    }
}

@Composable
fun Recipes(uiState: TestRecipeUiState, getRecipes: () -> Unit) {
    Button(onClick = { getRecipes() }) {
        Text(text = "getRecipes!")
    }
    when (uiState) {
        is TestRecipeUiState.Loaded -> {
            LazyColumn {
                items(uiState.recipes) {
                    Box(modifier = Modifier.border(1.dp, Color.Black)) {
                        Column {
                            Text(text = it.title)
                            Text(text = it.ingredients?.get(0).toString())
                        }
                    }
                }
            }
        }

        TestRecipeUiState.Empty -> {
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
            placeholder = { Text("Recipe name") },
            modifier = Modifier.fillMaxWidth()
        )

        var ingredientName by remember { mutableStateOf("") }
        TextField(
            value = ingredientName,
            onValueChange = {
                ingredientName = it
                ingredientForm.value = ingredientForm.value.copy(name = ingredientName)
            },
            placeholder = { Text("Ingredient name") },
            modifier = Modifier.fillMaxWidth()
        )

        var amount by remember { mutableStateOf("") }
        TextField(
            value = amount,
            onValueChange = {
                amount = it
                ingredientForm.value = ingredientForm.value.copy(amount = amount.toFloat())
            },
            placeholder = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        var expanded by remember { mutableStateOf(true) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(IngredientUnits.GRAM.name) },
                onClick = {
                    ingredientForm.value = ingredientForm.value.copy(unit = IngredientUnits.GRAM)
                })
            DropdownMenuItem(
                text = { Text(IngredientUnits.MIL.name) },
                onClick = {
                    ingredientForm.value = ingredientForm.value.copy(unit = IngredientUnits.GRAM)
                })
        }

        Button(
            onClick = {
                val recipe = Recipe(
                    recipeName,
                    listOf(ingredientForm.value),
                    listOf("instruction1", "instruction2")
                )
                addRecipe(recipe)
            },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Text("submit recipe ")
        }
    }
}