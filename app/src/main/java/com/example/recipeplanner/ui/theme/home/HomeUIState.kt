package com.example.recipeplanner.ui.theme.home

import com.example.recipeplanner.data.Recipe

data class HomeUIState(
    val recipes: List<Recipe>
)
sealed class HomeState {
    class Loaded(val recipes: List<Recipe>) : HomeState()
    data object Empty : HomeState()
}