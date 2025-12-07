package com.raaveinm.hybriddrive_android.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raaveinm.hybriddrive_android.data.internet.ApiService
import com.raaveinm.hybriddrive_android.data.internet.AuthSerialization
import com.raaveinm.hybriddrive_android.data.preferences.UserPreferencesRepository
import com.raaveinm.hybriddrive_android.data.preferences.userPreferencesDataStore
import kotlinx.coroutines.launch


class AuthScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferencesRepository =
        UserPreferencesRepository(application.userPreferencesDataStore)
    var isLoggedIn = mutableStateOf(false)
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    val errorMessage = mutableStateOf("")
    var register = mutableStateOf(false)


    fun register() {
        if (password.value != confirmPassword.value) {
            errorMessage.value = "Passwords do not match"
            return
        }
        viewModelScope.launch {
            try {
                val response = ApiService.authApi.register(
                    AuthSerialization(username = username.value, password = password.value)
                )
                if (response.isSuccessful) {
                    Log.i("AuthScreenViewModel", "Registration successful")
                } else {
                    Log.e(
                        "AuthScreenViewModel",
                        "Registration failed: ${response.errorBody()?.string()}"
                    )
                }
                login()
            } catch (e: Exception) {
                Log.e("AuthScreenViewModel", "Registration error: ${e.message}")
                errorMessage.value = "Registration error: ${e.message}"
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            try {
                val response = ApiService.authApi.login(
                    AuthSerialization(username = username.value, password = password.value)
                )
                if (response.isSuccessful) {
                    userPreferencesRepository.savePreferences(true)
                } else {
                    Log.e(
                        "AuthScreenViewModel",
                        "Login failed: ${response.errorBody()?.string()}"
                    )
                }
            isLoggedIn.value = userPreferencesRepository.isLogged()
            } catch (e: Exception) {
                Log.e("AuthScreenViewModel", "Login error: ${e.message}")
                errorMessage.value = "Login error: ${e.message}"
            }
        }
    }
}
