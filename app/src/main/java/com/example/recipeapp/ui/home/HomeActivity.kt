package com.example.recipeapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.adapter.ListRecipeMenu
import com.example.recipeapp.app.Constants
import com.example.recipeapp.db.AppDataBase
import com.example.recipeapp.db.dao.RecipeDAO
import com.example.recipeapp.db.dao.StepDAO
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.db.entities.Step
import com.example.recipeapp.ui.create.CreateActivity

import java.lang.Exception

class HomeActivity : AppCompatActivity(), HomeView {


    private var recipeList: List<Recipe> = emptyList()
    private var stepList: List<Step> = emptyList()
    private lateinit var adapterRecipes: ListRecipeMenu

    private var db: AppDataBase? = null
    private var stepDao: StepDAO? = null



    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_home)

            //init an Recipe Adapter with null value
            adapterRecipes = ListRecipeMenu(this@HomeActivity, recipeList)

            //init recycler view and set adapter
            val recyclerViewRecipe  = findViewById<RecyclerView>(R.id.recycler_view_recipes)
            recyclerViewRecipe.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)
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
            /*db = AppDataBase.getInstance(this@HomeActivity)
            recipeDao = db?.recipeDao()

            with(recipeDao){
                recipeList = this?.loadAllRecipes()!!
            }

            adapterRecipes.setRecipeMenu(recipeList)*/
            val mPresenter = HomePresenter(this@HomeActivity)
            mPresenter.loadAllRecipes()

        }catch (e: Exception){
            Log.e("HomeActivity", e.message)
        }
    }

    fun getRecipeView(recipe: Recipe){
        try {
            db = AppDataBase.getInstance(this@HomeActivity)
            stepDao = db?.stepDao()

            with(stepDao){
                stepList = this?.loadAllSteps(recipe.id)!!
            }
        }catch (e: Exception){
            Log.e("HomeActivity", e.message)
        }
    }


    fun createOwnRecipe(view: View){
        try{
            if(view != null){
                startActivity(Intent(this@HomeActivity, CreateActivity::class.java).apply {
                    putExtra(Constants.CALL_FROM, this@HomeActivity.resources.getInteger(R.integer.create_button_call))
                })
                finish()
            }
        }catch (e: Exception){
            Log.e("", e.message)
        }
    }

     override fun updateListMenu(recipeList: List<Recipe>) {
        adapterRecipes.setRecipeMenu(recipeList)
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
