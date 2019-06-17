package com.example.recipeapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.db.entities.Recipe

/**
 * Created by Vimosanan.A
 */

class ListRecipeMenu(var context: Context, var recipeList: MutableList<Recipe>):RecyclerView.Adapter<ListRecipeMenu.RecipeMenuViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeMenuViewHolder {
        // Inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_recipe_home_view, parent,false)

        // Return the view holder
        return RecipeMenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeMenuViewHolder, position: Int) {
        recipeList[position].let{recipe ->
            holder.bind(recipe)
        }
    }

    class RecipeMenuViewHolder(recipeMenuView: View):RecyclerView.ViewHolder(recipeMenuView){
        fun bind(recipe: Recipe){


        }


}
}