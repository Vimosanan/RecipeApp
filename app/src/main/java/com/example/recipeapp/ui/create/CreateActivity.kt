package com.example.recipeapp.ui.create

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipeapp.R
import com.example.recipeapp.app.Constants
import com.example.recipeapp.db.AppDataBase
import com.example.recipeapp.db.dao.RecipeDAO
import com.example.recipeapp.db.dao.StepDAO
import com.example.recipeapp.db.entities.Recipe
import com.example.recipeapp.db.entities.Step
import com.example.recipeapp.ui.home.HomeActivity
import com.example.recipeapp.util.RequestPermissionForImages
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_create_step.*
import java.lang.Exception


/**
 * @author Vimosanan.A
 */

class CreateActivity : AppCompatActivity(), CreateFragment.OnFragmentInteractionListener, CreateStepFragment.OnFragmentInteractionListener {

    private var originFrom: Int = 201
    private var data: Intent? = null
    private var recipe: Recipe = Recipe()
    private lateinit var stepList: ArrayList<Step>

    private var db: AppDataBase? = null
    private var recipeDao: RecipeDAO? = null
    private var stepDao: StepDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_create)

            originFrom = intent.getIntExtra(Constants.CALL_FROM, 201)

            //open the create activity fragment on the start
            openFragment(CreateFragment())

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

    override fun validateUserEntry(imageUri: Uri, name: String, timeNeeded: Int, ingredients: String, recipeId: Int) {
        try {
            if(imageUri == null){
                showMessage(resources.getString(R.string.empty_image))
            }else if(name == null || name == ""){
                showMessage(resources.getString(R.string.empty_name))
            }else if(timeNeeded <= 0){
                showMessage(resources.getString(R.string.empty_time))
            }else if(ingredients == null || ingredients == ""){
                showMessage(resources.getString(R.string.empty_ingredients))
            }else if(recipeId == 0){
                showMessage(resources.getString(R.string.empty_recipe_id))
            }else{
                recipe.name =name
                recipe.timeNeeded = timeNeeded
                recipe.ingredients = ingredients
                recipe.recipeTypeId = recipeId
                recipe.imageUrl = imageUri.toString()

                openFragment(CreateStepFragment())
            }
        }catch (e: Exception){
            Log.e("", e.message)
        }
    }

    override fun validateStepEntry(stepId: Int, imageUri: Uri, stepText: String) {
        try{
            if(imageUri == null){
                showMessage(resources.getString(R.string.empty_image))
            }else if(stepText == null || stepText == ""){
                showMessage(resources.getString(R.string.empty_step))
            }else{
                val stepFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as CreateStepFragment

                //increment the value
                var step = Step()
                step.recipeId = recipe.id
                step.stepId = stepId
                step.imageUrl = imageUri.toString()
                step.stepInfo = stepText

                stepList.add(step)

                stepFragment.openNextStep()
            }
        }catch(e: Exception){
            Log.e("", e.message)
        }
    }

    override fun finishRecipe() {
        //create an object of presenter class and call update method
        try{
            db = AppDataBase.getInstance(this@CreateActivity)
            recipeDao = db?.recipeDao()
            stepDao = db?.stepDao()

            with(recipeDao){
                this?.insetRecipe(recipe)
            }


            for(step in stepList!!){
                with(stepDao){
                    this?.insertStep(step)
                }
            }
        }catch(e: Exception){
            Log.e("", e.message)
        }
    }


    private fun showMessage(message: String){
        Toast.makeText(this@CreateActivity, message, Toast.LENGTH_LONG).show()
    }


    override fun uploadImageToRecipe(){
        val resultExternalStoragePermission = RequestPermissionForImages.checkPermissionForExternalStorage(this@CreateActivity)

        if (resultExternalStoragePermission) {
            galleryIntent(true)
        }
    }

    override fun uploadStepImage(step: Int){
        val resultExternalStoragePermission = RequestPermissionForImages.checkPermissionForExternalStorage(this@CreateActivity)

        if (resultExternalStoragePermission) {
            galleryIntent(false)
        }
    }

    private fun onSelectFromGalleryResult(data: Intent) {
        try{
            Glide.with(this)
                .load(data.data)
                .apply(RequestOptions().placeholder(R.drawable.deafult_image))
                .into(imgRecipeMain)
        }catch(e: Exception){
            Log.e("", e.message)
        }

    }

    private fun onSelectFromGalleryResultStep(data: Intent) {
        try{
            Glide.with(this)
                .load(data.data)
                .apply(RequestOptions().placeholder(R.drawable.deafult_image))
                .into(imgRecipeStep)
        }catch(e: Exception){
            Log.e("", e.message)
        }
    }

    private fun galleryIntent(isMain: Boolean) {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"
        if(isMain){
            startActivityForResult(intent, Constants.SELECT_FILE)
        }else{
            startActivityForResult(intent, Constants.SELECT_FILE_STEP)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    galleryIntent(true)
            } else {
                //code for deny
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            when (requestCode) {
                Constants.SELECT_FILE -> {
                    onSelectFromGalleryResult(data!!)
                    val mainFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as CreateFragment
                    mainFragment.setImageUri(data.data)

                }
                Constants.SELECT_FILE_STEP -> {
                    onSelectFromGalleryResultStep(data!!)
                    val stepFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as CreateStepFragment
                    stepFragment.setImageUri(data.data)
                }
            }
            this.data = data
        }
    }

}
