package com.example.recipeapp.ui.home

import android.content.Context
import android.os.AsyncTask
import com.example.recipeapp.db.AppDataBase
import com.example.recipeapp.db.dao.RecipeDAO
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.ui.BasePresenter

class HomePresenter(var context: Context):BasePresenter<HomeView>() {

    fun loadAllRecipes(){
        SelectAsyncTask(context).execute()
    }

    private class SelectAsyncTask(var context: Context): AsyncTask<String, Void, List<Recipe>>() {
        private var db: AppDataBase? = null
        private var recipeDao: RecipeDAO? = null
        private var recipeList: List<Recipe> = emptyList()

        override fun doInBackground(vararg params: String?): List<Recipe> {
            db = AppDataBase.getInstance(context)
            recipeDao = db?.recipeDao()

            with(recipeDao){
                recipeList = this?.loadAllRecipes()!!
            }
            return recipeList
        }


        override fun onPostExecute(result: List<Recipe>?) {
            super.onPostExecute(result)
        }
    }
}