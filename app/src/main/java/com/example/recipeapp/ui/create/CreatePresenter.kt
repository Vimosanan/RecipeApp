package com.example.recipeapp.ui.create

import android.os.AsyncTask
import android.util.Log
import androidx.room.Room
import com.example.recipeapp.db.AppDataBase
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.ui.BasePresenter

class CreatePresenter: BasePresenter<CreateView>() {

    fun updateDatabase(recipe: Recipe){
        Log.e("Presenter", "Update Database")

    }




}