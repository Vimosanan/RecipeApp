package com.example.recipeapp.db.dao

import androidx.room.*
import com.example.recipeapp.db.entities.Recipe


@Dao
interface RecipeDAO {
    @Insert
    fun insetRecipe(vararg recipe: Recipe)

    @Update
    fun updateRecipe(vararg recipe: Recipe)

    @Delete
    fun deleteRecipe(vararg recipe: Recipe)

    @Query("SELECT * from recipes")
    fun loadAllRecipes(): Array<Recipe>

    @Query("SELECT * FROM recipes WHERE recipe_recipe_type_id == :recipeTypeId")
    fun loadAllTypedRecipes(recipeTypeId: Int): Array<Recipe>

}