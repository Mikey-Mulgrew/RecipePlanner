package com.example.recipeplanner

import android.content.Context
import com.example.recipeplanner.ui.recipeBook.RecipeBookViewModelFactory
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeViewModelFactory

class MyContext(applicationContext: Context) {
    val recipeBookViewModelFactory = RecipeBookViewModelFactory(applicationContext)

    val createRecipeViewModelFactory = CreateRecipeViewModelFactory(applicationContext)
}