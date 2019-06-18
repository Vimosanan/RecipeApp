package com.example.recipeapp.app

interface Constants {
    companion object {
        val CALL_FROM: String get() = "call_from"
        val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE : Int get()= 123
        val SELECT_FILE : Int get()= 1
        val SELECT_FILE_STEP : Int get()= 2
    }
}