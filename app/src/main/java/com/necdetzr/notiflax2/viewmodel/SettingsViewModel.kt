package com.necdetzr.notiflax2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.necdetzr.notiflax2.data.datastore.DataStoreManager

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreManager = DataStoreManager(application)

    val languageCode = dataStoreManager.languageCodeFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, "en")

    fun setLanguage(lang:String){
        viewModelScope.launch {
            dataStoreManager.setLanguageCode(lang)
        }
    }
}