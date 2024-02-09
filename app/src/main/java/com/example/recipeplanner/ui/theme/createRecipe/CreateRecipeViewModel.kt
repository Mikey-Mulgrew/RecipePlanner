package com.example.recipeplanner.ui.theme.createRecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    private val _uiState = MutableStateFlow(CreateRecipeUIState(
        title = "",
        nextIngredient = Ingredient("", 0f),
        ingredients  = emptyList(),
        nextInstruction = "",
        instructions = emptyList(),
        sendAction = ::handleAction
    ))
    val uiState: StateFlow<CreateRecipeUIState> = _uiState


    private fun handleAction( action: RecipeAction) {
        when(action){
            is RecipeAction.UpdateRecipeTitle -> updateRecipeTitle(action.title)
            is RecipeAction.UpdateIngredient -> updateIngredient(action.ingredient)
            is RecipeAction.AddIngredient -> addIngredient(action.ingredient)
            is RecipeAction.RemoveIngredient -> TODO()
            is RecipeAction.AddInstruction -> TODO()
            is RecipeAction.RemoveInstruction -> TODO()
            is RecipeAction.SaveRecipe -> saveRecipe()
        }
    }

    private fun updateRecipeTitle(title: String){
        _uiState.update { it.copy(title = title) }
    }

    private fun updateIngredient(ingredient: Ingredient){

        _uiState.update { it.copy(nextIngredient = ingredient) }
    }
    private fun addIngredient(ingredient: Ingredient){
        _uiState.update { it.copy(nextIngredient = ingredient, ingredients = _uiState.value.ingredients.plus(ingredient)) }
    }
    private fun removeIngredient(ingredient: Ingredient){

    }
    private fun addInstruction(instruction: String){

    }
    private fun removeInstruction(instruction: String){

    }

    private fun saveRecipe(){
        viewModelScope.launch {
            repository.addRecipe(Recipe(uiState.value.title, uiState.value.ingredients))
        }
    }



}