@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.recipeplanner.ui.theme.createRecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeplanner.R
import com.example.recipeplanner.ui.theme.RecipePlannerTheme

@Preview(device = "id:pixel_6")
@Composable
private fun CreateRecipeScreenPreview() {
    RecipePlannerTheme{
        CreateRecipeScreen(
            CreateRecipeUIState(
                title = "Gumbo",
                nextIngredient = IngredientItem("", 0f),
                ingredients  = emptyList(),
                nextInstruction = "",
                instructions = emptyList(),
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(
    uiState: CreateRecipeUIState
){
    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            CreateRecipeAppBar(
                onSavePressed = { uiState.sendAction(RecipeAction.SaveRecipe) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.primary)
            )
        }
    ) { contentPadding ->
        CreateRecipeLayout(
            uiState,
            modifier = Modifier.padding(contentPadding))
    }
}

@Composable
private fun CreateRecipeAppBar(
//    onBackClicked: () -> Unit,
    onSavePressed: () -> Unit,
    modifier: Modifier = Modifier){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = "Add Recipe",
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
        )
        Button(
            onClick = { onSavePressed() },
            Modifier.background(Color.DarkGray),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Save",
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun CreateRecipeLayout(
    uiState: CreateRecipeUIState,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .systemBarsPadding()
//            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        recipeInputSection(
            sectionTitle = "Title",
            userEntry = uiState.title,
            labelString = "Enter a name for your recipe",
            onTitleChange = { uiState.sendAction(RecipeAction.UpdateRecipeTitle(it)) }
        )

        // Figure out input being reflected in the outline box and add to state and save to lazy col.
        recipeInputSection(
            sectionTitle = "Ingredients",
            userEntry = uiState.nextIngredient.name,
            labelString = "Add your first ingredient",
            onTitleChange = { uiState.sendAction(RecipeAction.UpdateIngredient(IngredientItem(it, 10f))) },
            onKeyboardDone = { uiState.sendAction(RecipeAction.AddIngredient(uiState.nextIngredient))}
        )
        if (uiState.ingredients.isNotEmpty()){
            LazyColumn {
                items(uiState.ingredients) {
                    Box(modifier = Modifier.fillMaxWidth()
                        .shadow(-2.dp)
                        .background(color = Color.Cyan,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ){ Text(
                        text = "${it.name} ${it.amount}Gs",
                        modifier = Modifier.padding(start = 12.dp)
                        ) }
                }

            }
        }
        recipeInputSection(
            sectionTitle = "Instructions",
            userEntry = uiState.nextInstruction,
            labelString = "Add your step by step instructions",
            onTitleChange = { }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun recipeInputSection(
    sectionTitle: String,
    userEntry: String,
    labelString: String,
    onTitleChange:  (String) -> Unit,
    onKeyboardDone: ()-> Unit = {}
){
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = 12.dp)
        ) {
        Text(
            text = sectionTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        OutlinedTextField(
            value = userEntry,
            onValueChange = onTitleChange,
            shape = shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = labelString) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            )
        )

    }
}

