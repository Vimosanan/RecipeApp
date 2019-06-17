package com.example.recipeapp.ui.create

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import com.example.recipeapp.R
import java.lang.Exception


/**
 * @author Vimosanan.A
 */

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_create)
        }catch (e: Exception){
            Log.e("", e.message)
        }
    }

}
