package com.example.recipeplanner

import android.content.Context
import com.example.recipeplanner.ui.recipeBook.RecipeBookViewModelFactory

class MyContext(applicationContext: Context) {
    val recipeBookViewModelFactory = RecipeBookViewModelFactory(applicationContext)
}