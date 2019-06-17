package com.example.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.db.entities.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun recipe(): Recipe

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase?{
            if(INSTANCE == null){
                synchronized(AppDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "recipes.db")
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