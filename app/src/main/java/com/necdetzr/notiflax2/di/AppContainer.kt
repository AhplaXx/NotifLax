package com.necdetzr.notiflax2.di

import android.app.Application
import com.necdetzr.notiflax2.data.repository.AuthRepository

class AppContainer(application: Application) {
    val authRepository = AuthRepository()
    val authViewModelFactory = AuthViewModelFactory(authRepository)
}