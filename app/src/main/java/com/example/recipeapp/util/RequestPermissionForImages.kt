package com.example.recipeapp.util

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.recipeapp.app.Constants

class RequestPermissionForImages {
    companion object {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        fun checkPermissionForExternalStorage(context: Context): Boolean {
            val currentAPIVersion = Build.VERSION.SDK_INT
            if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            context as Activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    ) {
                        val alertBuilder = AlertDialog.Builder(context)
                        alertBuilder.setCancelable(true)
                        alertBuilder.setTitle("Permission necessary")
                        alertBuilder.setMessage("External storage permission is necessary")
                        alertBuilder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            ActivityCompat.requestPermissions(
                                context, arrayOf(
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                                ), Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                            )
                        }
                        val alert = alertBuilder.create()
                        alert.show()

                    } else {
                        ActivityCompat.requestPermissions(
                            context,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                        )
                    }
                    return false
                } else {
                    return true
                }
            } else {
                return true
            }
        }
    }
}