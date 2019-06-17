package com.example.recipeapp.ui.create

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipeapp.R
import kotlinx.android.synthetic.main.fragment_create.*

/**
 * A placeholder fragment containing a simple view.
 */
class CreateActivityFragment : Fragment() {
    private lateinit var name: String
    private var timeNeeded: Int = 0
    private var recipeTypeId: Int = 0
    private lateinit var ingredients: String
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        btnNextCreateMain.setOnClickListener{
            name = editName.text.toString().trim()
            timeNeeded = Integer.parseInt(editTime.text.toString().trim())
            ingredients = editIngredients.text.toString().trim()
            recipeTypeId = 0

            listener?.validateUserEntry(name, timeNeeded, ingredients, recipeTypeId)
        }
        return inflater.inflate(R.layout.fragment_create, container, false)
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

    interface OnFragmentInteractionListener {
        fun validateUserEntry(name: String, timeNeeded: Int, ingredients: String, recipeId: Int)
    }
}
