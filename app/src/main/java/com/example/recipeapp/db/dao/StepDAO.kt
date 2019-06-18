package com.example.recipeapp.db.dao

import androidx.room.*
import com.example.recipeapp.db.entities.Step

@Dao
interface StepDAO {
    @Insert
    fun insertStep(vararg step: Step)

    @Update
    fun updateRecipe(vararg step: Step)

    @Delete
    fun deleteRecipe(vararg step: Step)

    @Query("SELECT * FROM steps WHERE recipe_id == :recipeId")
    fun loadAllSteps(recipeId: Long): List<Step>
}