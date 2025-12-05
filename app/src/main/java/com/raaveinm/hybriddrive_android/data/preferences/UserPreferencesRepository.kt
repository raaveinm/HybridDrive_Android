package com.raaveinm.hybriddrive_android.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "user_prefs"
val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATA_STORE_NAME
)

class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val IS_LOGGED = booleanPreferencesKey("is_logged")
    }

    suspend fun savePreferences (
        isLogged: Boolean,
        userName: String?,
        userPassword: String?
    ) {
        dataStore.edit { preferences ->

            preferences[IS_LOGGED] = isLogged

            Log.v("UserPreferencesRepository", "Saving preferences with data" +
                    "isLogged: $isLogged")
        }
    }

    suspend fun logout() {
        savePreferences(false, null, null)
    }

    suspend fun isLogged(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[IS_LOGGED] ?: false
    }

    val isLoggedFlow: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[IS_LOGGED] ?: false
        }
}
