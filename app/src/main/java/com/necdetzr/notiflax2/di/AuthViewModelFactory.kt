package com.necdetzr.notiflax2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.necdetzr.notiflax2.data.repository.AuthRepository
import com.necdetzr.notiflax2.viewmodel.AuthViewModel

class AuthViewModelFactory(private val authRepository: AuthRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
                return AuthViewModel(authRepository) as T
            }
        throw IllegalArgumentException("Unknown ViewModel Class")




    }

}