package com.example.recipeapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.adapter.ListRecipeMenu
import com.example.recipeapp.db.entities.Recipe

import java.lang.Exception

class HomeActivity : AppCompatActivity() {

    private var recipeList: ArrayList<Recipe> = ArrayList()
    private lateinit var adapterRecipes: ListRecipeMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)

            //init an Recipe Adapter with null value
            adapterRecipes = ListRecipeMenu(this@HomeActivity, recipeList)

            //init recycler view and set adapter
            val recyclerViewRecipe  = findViewById<RecyclerView>(R.id.recycler_view_recipes)
            recyclerViewRecipe.adapter = adapterRecipes

            //init drop down view to select recipe types
            val recipeTypeSpinner = findViewById<Spinner>(R.id.recipe_type_spinner)
            ArrayAdapter.createFromResource(
                this,
                R.array.recipe_types,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                recipeTypeSpinner.adapter = adapter
            }

            getAllRecipeMenu()


        }catch (e: Exception){
            Log.e("HomeActivity", e.message)
        }
    }

    private fun getAllRecipeMenu() {
        try {

        }catch (e: Exception){
            Log.e("HomeActivity", e.message)
        }
    }

    fun getRecipeView(recipeId: Int){
        try {

        }catch (e: Exception){
            Log.e("HomeActivity", e.message)
        }
    }

    class MyOnItemSelectedListenerRecipeType: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            try {

            }catch (e: Exception){
                Log.e("HomeActivity", e.message)
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //Do nothing
        }


    }

}
