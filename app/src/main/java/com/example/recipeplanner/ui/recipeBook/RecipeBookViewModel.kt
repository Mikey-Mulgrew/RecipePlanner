package com.example.recipeplanner.ui.recipeBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class HomeScreenUiState {
    class Loaded(val recipes: List<Recipe>) : HomeScreenUiState()

    object Empty : HomeScreenUiState()
}

class HomeScreenViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Empty)
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = repository.recipes()
            if (recipes.isEmpty())
                _uiState.value = HomeScreenUiState.Empty
            else
                _uiState.value = HomeScreenUiState.Loaded(recipes)
        }
    }
}