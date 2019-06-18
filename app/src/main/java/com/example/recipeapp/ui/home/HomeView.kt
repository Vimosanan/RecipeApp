package com.example.recipeapp.ui.home

import com.example.recipeapp.db.entities.Recipe

interface HomeView{
    fun updateListMenu(recipeList: List<Recipe>)
}