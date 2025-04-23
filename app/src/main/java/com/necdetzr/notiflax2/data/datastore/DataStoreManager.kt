package com.necdetzr.notiflax2.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.necdetzr.notiflax2.data.datastore.PreferenceKeys.HAS_SEEN_SECOND_WELCOME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//Data Store defined as settings
private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager(private val context:Context){
    val languageCodeFlow: Flow<String> = context.dataStore.data.map { preferences->
        preferences[PreferenceKeys.LANGUAGE] ?: "en"
    }
    val darkModeFlow:Flow<Boolean> = context.dataStore.data.map { preferences->
        preferences[PreferenceKeys.DARK_MODE] ?: false
    }
    val rememberMeFlow:Flow<Boolean> = context.dataStore.data.map { preferences->
        preferences[PreferenceKeys.REMEMBER_ME] ?: false
    }

    val hasSeenSecondWelcomeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[HAS_SEEN_SECOND_WELCOME] ?: false
        }

    suspend fun setHasSeenSecondWelcome(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HAS_SEEN_SECOND_WELCOME] = value
        }
    }
    suspend fun setLanguageCode(lang:String){
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.LANGUAGE] = lang

        }
    }
    suspend fun setRememberMe(rememberMe:Boolean){
        context.dataStore.edit { preferences->
            preferences[PreferenceKeys.REMEMBER_ME] = rememberMe
        }
    }
    suspend fun saveSignIn(isLoggedIn:Boolean){
        context.dataStore.edit { preferences->
            preferences[PreferenceKeys.IS_LOGGED_IN] = isLoggedIn

        }
    }
    suspend fun setDarkMode(darkMode:Boolean){
        context.dataStore.edit { preferences->
            preferences[PreferenceKeys.DARK_MODE] = darkMode
        }
    }

}