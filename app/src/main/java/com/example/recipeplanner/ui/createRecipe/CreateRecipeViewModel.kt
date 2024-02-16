package com.example.recipeplanner.ui.createRecipe

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipeplanner.data.Ingredient
import com.example.recipeplanner.data.Recipe
import com.example.recipeplanner.data.RecipeRepository
import com.example.recipeplanner.data.recipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateRecipeViewModel(private val repository: RecipeRepository): ViewModel() {

    private val _uiState = MutableStateFlow(
        CreateRecipeUIState(
        title = "",
        nextIngredient = Ingredient("", 0f),
        ingredients  = emptyList(),
        nextInstruction = "",
        instructions = emptyList(),
        sendAction = ::handleAction
    )
    )
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
        _uiState.update { it.copy(nextInstruction = instruction, instructions = _uiState.value.instructions.plus(instruction)) }
    }
    private fun removeInstruction(instruction: String){

    }

    private fun saveRecipe(){
        viewModelScope.launch {
            repository.addRecipe(Recipe(0,uiState.value.title, uiState.value.ingredients, uiState.value.instructions))
        }
    }
}

class CreateRecipeViewModelFactory(private val applicationContext: Context) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = CreateRecipeViewModel(recipeRepository(applicationContext))
        return viewModel as T
    }
}