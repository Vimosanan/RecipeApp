package com.example.recipeapp.ui.create


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_create_step.*
import kotlinx.android.synthetic.main.fragment_create_step.view.*
import java.lang.Exception

/**
 * A placeholder fragment containing a simple view.
 */
class CreateStepFragment : Fragment() {
    private lateinit var step: String
    private var stepInstance: Int = 1
    private lateinit var imageUri: Uri

    private lateinit var txtStep: TextView
    private lateinit var btnFinish:Button
    private lateinit var editStep:EditText
    private lateinit var btnNext:Button
    private lateinit var btnImageStepUpload:ImageButton
    private lateinit var imageViewStep: ImageView

    private var listener: CreateStepFragment.OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_step, container, false)

        editStep = view.editStep
        btnNext = view.btnNext
        btnFinish = view.btnFinish
        txtStep = view.textStep
        btnImageStepUpload = view.imgBtnStepUpload
        imageViewStep = view.imgRecipeStep


        txtStep.text = resources.getString(R.string.text_view_step) + stepInstance

        btnNext.setOnClickListener{
            step = editStep.text.toString().trim()
            //call for validation
            listener?.validateStepEntry(stepInstance, imageUri, step)
        }

        btnFinish.setOnClickListener{
            //check for at least one step and put to database
            listener?.finishRecipe()
        }

        btnImageStepUpload.setOnClickListener{
            listener?.uploadStepImage(stepInstance)
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

    fun openNextStep(){
        try{
            editStep.text.clear()
            stepInstance += 1

            txtStep.text = resources.getString(R.string.text_view_step) + stepInstance
            try{
                Glide.with(this)
                    .load(RequestOptions().placeholder(R.drawable.deafult_image))
                    .apply(RequestOptions().placeholder(R.drawable.deafult_image))
                    .into(imgRecipeStep)
            }catch(e: Exception){
                Log.e("", e.message)
            }

        }catch(e: Exception){
            Log.e("", e.message)
        }
    }

    fun setImageUri(imageUri: Uri){
        this.imageUri = imageUri
    }

    interface OnFragmentInteractionListener {
        fun validateStepEntry(id: Int, imageUri: Uri, step: String)
        fun uploadStepImage(stepInstance: Int)
        fun finishRecipe()
    }
}
