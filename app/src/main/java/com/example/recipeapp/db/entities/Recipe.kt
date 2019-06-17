package com.example.recipeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe (

    /*
    * Represent a recipe table in database
    */
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "recipe_name") val name: String?,
    @ColumnInfo(name = "recipe_author") val author: String?,
    @ColumnInfo(name = "recipe_steps_count") val stepsCount: Int?,
    @ColumnInfo(name = "recipe_image_url") val imageUrl: String?,
    @ColumnInfo(name = "recipe_time_needed") val timeNeeded: String?,
    @ColumnInfo(name = "recipe_steps") val steps: String?,
    @ColumnInfo(name = "recipe_recipe_type_id") val recipeTypeId: String?,
    @ColumnInfo(name = "recipe_ingredients") val ingredients: String?

)