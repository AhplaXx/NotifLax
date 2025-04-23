package com.necdetzr.notiflax2

import android.app.Application
import com.google.firebase.FirebaseApp
import com.necdetzr.notiflax2.di.AppContainer

class NotifLaxApplication : Application() {
    lateinit var appContainer:AppContainer
    override fun onCreate() {

        super.onCreate()
        FirebaseApp.initializeApp(this)

        appContainer = AppContainer(this)
    }
}