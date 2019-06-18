package com.example.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.db.dao.RecipeDAO
import com.example.recipeapp.db.dao.StepDAO
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.db.entities.Step

@Database(entities = [Recipe::class, Step::class],  version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO
    abstract fun stepDao(): StepDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase?{
            if(INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "recipes.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }


        fun destroyInstance(){
            INSTANCE = null
        }
    }
}