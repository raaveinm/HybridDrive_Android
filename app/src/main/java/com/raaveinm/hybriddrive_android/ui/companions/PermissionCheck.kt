package com.raaveinm.hybriddrive.ui.companions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val TAG = "Permission"
const val REQUEST_CODE = 100
const val REQUEST_CODE_MANAGE_STORAGE = 101


fun mediaPermission(activity: Activity) {
    photoAndVideoPolicy(activity)
}

fun photoAndVideoPolicy(activity: Activity) {

    Log.i(TAG, "Permission \"Media\" check")

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
        )
    } else
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO
        )

    val permissionsToRequest = permissions.filter {
        Log.i(TAG, "Permission request list")
        ContextCompat.checkSelfPermission(activity, it)!=
                PackageManager.PERMISSION_GRANTED }.toTypedArray()

    if (permissionsToRequest.isNotEmpty()) {
        Log.i(TAG, "Permission request")
        ActivityCompat.requestPermissions(
            activity,
            permissionsToRequest,
            REQUEST_CODE
        )
    } else {
        Log.i(TAG, "Permission already granted")
    }
}

fun storageAccess(activity: Activity) {

    Log.i(TAG, "Permission check \"Storage\"")


}
