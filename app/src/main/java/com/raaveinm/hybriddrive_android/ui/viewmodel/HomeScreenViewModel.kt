package com.raaveinm.hybriddrive_android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.raaveinm.hybriddrive_android.data.internet.FileList
import com.raaveinm.hybriddrive_android.data.preferences.UserPreferencesRepository
import com.raaveinm.hybriddrive_android.data.preferences.userPreferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferencesRepository =
        UserPreferencesRepository(application.userPreferencesDataStore)
    private val _files = MutableStateFlow<List<FileList>>(emptyList())
    val files: StateFlow<List<FileList>> = _files
    private val _isLogged = MutableStateFlow<Boolean?>(null)
    var isLogged = _isLogged.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
}
