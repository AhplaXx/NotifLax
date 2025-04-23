package com.necdetzr.notiflax2.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val LANGUAGE = stringPreferencesKey("language")
    val DARK_MODE = booleanPreferencesKey("dark_mode")
    val REMEMBER_ME = booleanPreferencesKey("remember_me")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val HAS_SEEN_SECOND_WELCOME = booleanPreferencesKey("has_seen_second_welcome")

}