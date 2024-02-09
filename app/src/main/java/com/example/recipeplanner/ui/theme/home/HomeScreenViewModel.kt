package com.example.recipeplanner.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.ui.theme.createRecipe.CreateRecipeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



class HomeScreenViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.Empty)
    val uiState: StateFlow<HomeState> = _uiState

    fun addRecipe(title: String) {
        viewModelScope.launch {
            val recipe = Recipe(title)
            repository.addRecipe(recipe)
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = repository.recipes()

            if (recipes.isEmpty())
                _uiState.value = HomeState.Empty
            else
                _uiState.value = HomeState.Loaded(recipes.map { Recipe(it.title) })
        }
    }
}