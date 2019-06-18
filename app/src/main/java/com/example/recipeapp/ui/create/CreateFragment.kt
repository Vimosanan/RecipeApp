package com.example.recipeapp.ui.create

import android.content.Context
import android.media.Image
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_create.view.*
import java.lang.Exception

/**
 * A placeholder fragment containing a simple view.
 */
class CreateFragment : Fragment() {
    private lateinit var name: String
    private var timeNeeded: Int = 0
    private var recipeTypeId: Int = 0
    private lateinit var ingredients: String
    private lateinit var imageUri: Uri

    private lateinit var btnNext: Button
    private lateinit var editName: EditText
    private lateinit var editTime: EditText
    private lateinit var editIngredients: EditText
    private lateinit var imgBtnUploadPhoto: ImageButton


    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create, container, false)

        btnNext = view.btnNextCreateMain
        editName = view.editName
        editTime = view.editTime
        editIngredients = view.editIngredients
        imgBtnUploadPhoto = view.imgBtnUploadPhoto


        try {
            btnNext.setOnClickListener{
                name = editName.text.toString().trim()
                timeNeeded = editTime.text.toString().trim().toInt()
                ingredients = editIngredients.text.toString().trim()
                recipeTypeId = 1

                listener?.validateUserEntry(imageUri, name, timeNeeded, ingredients, recipeTypeId)
            }

            imgBtnUploadPhoto.setOnClickListener{
                listener?.uploadImageToRecipe()
            }
        }catch (e: Exception){
            Log.e("", e.message)
        }

        return view
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    fun setImageUri(imageUri: Uri){
        this.imageUri = imageUri
    }

    interface OnFragmentInteractionListener {
        fun validateUserEntry(imageUri: Uri, name: String, timeNeeded: Int, ingredients: String, recipeId: Int)
        fun uploadImageToRecipe()
    }
}
