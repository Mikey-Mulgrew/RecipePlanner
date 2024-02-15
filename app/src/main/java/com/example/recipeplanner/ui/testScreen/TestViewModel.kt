package com.example.recipeplanner.ui.testScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TestRecipeUiState {
    class Loaded(val recipes: List<Recipe>) : TestRecipeUiState()

    object Empty : TestRecipeUiState()
}

class TestScreenViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<TestRecipeUiState>(TestRecipeUiState.Empty)
    val uiState: StateFlow<TestRecipeUiState> = _uiState

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = repository.recipes()
            if (recipes.isEmpty())
                _uiState.value = TestRecipeUiState.Empty
            else
                _uiState.value = TestRecipeUiState.Loaded(recipes)
        }
    }
}