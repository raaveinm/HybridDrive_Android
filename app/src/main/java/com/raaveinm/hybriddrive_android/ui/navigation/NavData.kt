package com.raaveinm.hybriddrive_android.ui.navigation

import com.raaveinm.hybriddrive_android.data.internet.FileList

sealed class NavData(val route: String) {
    object Auth : NavData("auth")
    object Files : NavData("files")
    object Upload : NavData("upload")
    data class View(val file: FileList) : NavData("preview")
}