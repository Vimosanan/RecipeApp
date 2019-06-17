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
    @ColumnInfo(name = "recipe_name") var name: String?,
    @ColumnInfo(name = "recipe_author") var author: String?,
    @ColumnInfo(name = "recipe_steps_count") var stepsCount: Int?,
    @ColumnInfo(name = "recipe_image_url") var imageUrl: String?,
    @ColumnInfo(name = "recipe_time_needed") var timeNeeded: String?,
    @ColumnInfo(name = "recipe_steps") var steps: String?,
    @ColumnInfo(name = "recipe_recipe_type_id") var recipeTypeId: String?,
    @ColumnInfo(name = "recipe_ingredients") var ingredients: String?

)