package com.example.recipeplanner.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class RecipeUiState {
    class Loaded(val recipes: List<Recipe>) : RecipeUiState()

    object Empty : RecipeUiState()
}

class HomeScreenViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Empty)
    val uiState: StateFlow<RecipeUiState> = _uiState

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = repository.recipes()

            if (recipes.isEmpty())
                _uiState.value = RecipeUiState.Empty
            else
                _uiState.value = RecipeUiState.Loaded(recipes)
        }
    }
}