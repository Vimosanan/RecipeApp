package com.example.recipeapp.ui.create

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.example.recipeapp.R
import com.example.recipeapp.app.Constants
import com.example.recipeapp.ui.home.HomeActivity
import java.lang.Exception


/**
 * @author Vimosanan.A
 */

class CreateActivity : AppCompatActivity(), CreateActivityFragment.OnFragmentInteractionListener {


    var originFrom: Int = 201

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_create)

            originFrom = intent.getIntExtra(Constants.CALL_FROM, 201)

            //open the create activity fragment on the start
            openFragment(CreateActivityFragment())

        }catch (e: Exception){
            Log.e("", e.message)
        }
    }


    override fun onBackPressed() {
        if (originFrom == this@CreateActivity.resources.getInteger(R.integer.create_button_call) && true) {
            startActivity(Intent(this@CreateActivity, HomeActivity::class.java))
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        try {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }catch (e: Exception){
            Log.e("", e.message)
        }
    }

    override fun validateUserEntry(name: String, timeNeeded: Int, ingredients: String, recipeId: Int) {
        try {
            if(name == null || name == ""){
                //input a name
            }else if(timeNeeded > 0){
                //please Enter a valid time duration
            }else if(ingredients == null || ingredients == ""){
                //type the ingredients
            }else if(recipeId == 0){
                //please select a recipe type
            }else{
                openFragment(CreateStepFragment())
            }
        }catch (e: Exception){
            Log.e("", e.message)
        }
    }
}
