package com.example.recipeplanner.ui.theme.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTest(uiState: HomeState, getRecipes: () -> Unit, addRecipe: (String) -> Unit) {
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
fun Recipes(uiState: HomeState) {
    when (uiState) {
        is HomeState.Loaded -> {
            LazyColumn {
                items(uiState.recipes) {
                    Box(modifier = Modifier.border(1.dp, Color.Black)){ Text(text = it.title) }
                }
            }
        }

        HomeState.Empty -> {
            Text("nothing!")
        }
    }
}