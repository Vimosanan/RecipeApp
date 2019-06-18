package com.example.recipeapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps")
data class Step (
    @PrimaryKey(autoGenerate = true) var id: Long? = 0,
    @ColumnInfo(name = "recipe_id") var recipeId: Long? = null,
    @ColumnInfo(name = "step_id") var stepId: Int? =null,
    @ColumnInfo(name = "step_info") var stepInfo: String? =null,
    @ColumnInfo(name = "image_uri") var imageUrl: String? = null
)