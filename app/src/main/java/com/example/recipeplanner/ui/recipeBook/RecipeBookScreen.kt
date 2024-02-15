package com.example.recipeplanner.ui.recipeBook

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.IngredientUnits
import com.example.recipeplanner.data.Recipe


fun ingredients(): List<Ingredient> = listOf(
    Ingredient("ingredient1", 1f, IngredientUnits.GRAM),
    Ingredient("ingredient2", 2f, IngredientUnits.MIL)
)

fun getStaticRecipes(): List<Recipe> = listOf(
    Recipe("test1", ingredients = ingredients()), Recipe("test2", ingredients = ingredients())
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBookScreen(viewModel: RecipeBookViewModel, addRecipeClicked: () -> Unit) {
    LaunchedEffect(Unit) {
        getStaticRecipes().forEach {
            viewModel.addRecipe(it)
        }
    }

    viewModel.getRecipes()
    Scaffold(
        floatingActionButton = { AddRecipeButton(onClick = addRecipeClicked) },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Box(modifier = Modifier.padding(it)) {
            RecipeList(uiState = viewModel.uiState.collectAsState().value)
        }
    }
}

@Composable
fun RecipeList(uiState: HomeScreenUiState) {
    when (uiState) {
        HomeScreenUiState.Empty -> Text("Empty!")
        is HomeScreenUiState.Loaded -> {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                items(uiState.recipes) {
                    RecipePanel(it)
                }
            }
        }
    }
}

@Composable
fun RecipePanel(recipe: Recipe) {
    Column(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .padding(2.dp)
    ) {
        Text(recipe.title)
        recipe.ingredients?.let { ingredient ->
            LazyRow(contentPadding = PaddingValues(2.dp)) {
                items(ingredient) {
                    IngredientPanel(it)
                }
            }
        }
    }
}

@Composable
fun IngredientPanel(ingredient: Ingredient) {
    val ingredientShape = RoundedCornerShape(6.dp)
    val ingredientTextColour = Color.White
    Row(
        modifier = Modifier
            .border(1.dp, Color.Black, shape = ingredientShape)
            .background(Color.Red, shape = ingredientShape)
            .padding(2.dp)
    ) {
        Text(
            text = ingredient.name, color = ingredientTextColour
        )
        Text(
            text = ingredient.amount.toString(), color = ingredientTextColour
        )
        Text(
            text = ingredient.unit.name, color = ingredientTextColour
        )
    }
}

@Composable
fun AddRecipeButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(Icons.Filled.Add, "add recipe button")
    }
}