package com.example.recipeapp.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.R
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.ui.home.HomeActivity
import kotlinx.android.synthetic.main.card_view_recipe_home_view.view.*

/**
 * Created by Vimosanan.A
 */

class ListRecipeMenu(var context: Context, var recipeList: List<Recipe>):RecyclerView.Adapter<ListRecipeMenu.RecipeMenuViewHolder>(){
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

    fun setRecipeMenu(recipeList: List<Recipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    class RecipeMenuViewHolder(recipeMenuView: View):RecyclerView.ViewHolder(recipeMenuView){
        fun bind(recipe: Recipe){
            itemView.cv_recipe_name.text = recipe.name
            itemView.cv_recipe_time.text = recipe.timeNeeded.toString()
            itemView.cv_recipe_type.text = recipe.recipeTypeId.toString()
            Glide.with(itemView.context)
                .load(Uri.parse(recipe.imageUrl))
                .apply(RequestOptions().placeholder(R.drawable.deafult_image))
                .into(itemView.cv_image)

            itemView.cv_menu.setOnClickListener{
                (itemView.context as HomeActivity).getRecipeView(recipe)
            }
        }


}
}