package com.example.recipeapp.ui

open class BasePresenter<T> {

    var mView: T? = null

    fun attachView(view: T) {
        this.mView = view
    }

    fun detachView() {
        if (mView != null) {
            mView = null
        }
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }
}