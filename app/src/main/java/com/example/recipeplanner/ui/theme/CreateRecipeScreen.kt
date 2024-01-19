@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.recipeplanner.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeplanner.R

@Preview(device = "id:pixel_6")
@Composable
private fun CreateRecipeScreenPreview() {
    RecipePlannerTheme{
        CreateRecipePage()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipePage(){
    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            CreateRecipeAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    ) { contentPadding ->
        CreateRecipeScreen(modifier = Modifier.padding(contentPadding))
    }
}

@Composable
private fun CreateRecipeAppBar(
//    onBackClicked: () -> Unit,
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
            onClick = { /*TODO*/ }
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
private fun CreateRecipeScreen(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            BoldHeaderText(string = "Title")
            MyOutlinedTextField("Give your recipe a name")
        }
    }
}

@Composable
private fun BoldHeaderText(
    string:String
){
    Text(
        text = string ,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyOutlinedTextField(
    labelString: String
){
    OutlinedTextField(
        value = "Temporary Value - to come from View Model",
        onValueChange = {},
        shape = shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelString)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
//                keyboardActions = KeyboardActions(
//                    onDone = { onKeyboardDone() }
//                )
        //        colors = TextFieldDefaults.colors(
//            focusedContainerColor = colorScheme.surface,
//            unfocusedContainerColor = colorScheme.surface,
//            disabledContainerColor = colorScheme.surface,
//        ),
    )
}

