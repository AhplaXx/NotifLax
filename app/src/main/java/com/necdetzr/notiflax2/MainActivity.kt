package com.necdetzr.notiflax2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.data.datastore.DataStoreManager
import com.necdetzr.notiflax2.data.repository.AuthRepository
import com.necdetzr.notiflax2.di.AuthViewModelFactory
import com.necdetzr.notiflax2.ui.theme.NotifLaxTheme
import com.necdetzr.notiflax2.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var dataStoreManager: DataStoreManager


    override fun attachBaseContext(newBase: Context?) {
        dataStoreManager = DataStoreManager(newBase!!)
        runBlocking {
            val langCode = dataStoreManager.languageCodeFlow.first()
            val locale = Locale(langCode)
            Locale.setDefault(locale)
            val config = Configuration(newBase.resources.configuration)
            config.setLocale(locale)
            applyOverrideConfiguration(config)
        }
        super.attachBaseContext(newBase)

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }

        //!Initialize Firebase on app startup
        FirebaseApp.initializeApp(this)
        //*AppContainer for dependency injection
        val appContainer = (application as NotifLaxApplication).appContainer
        //*AuthRepository for authentication
        val authRepository = appContainer.authRepository
        //*AuthViewModelFactory for creating AuthViewModel with Factory Method
        val factory =AuthViewModelFactory(authRepository)
        //*Catching Auth_View_Model with factory and assigning it to authViewModel
        authViewModel =ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        //*Make the app edge to edge compatible
        enableEdgeToEdge()



        setContent {
            NotifLaxTheme {
                enableEdgeToEdge()
                //Application Composable with authViewModel
                NotifLaxApp(authViewModel)
            }
        }
    }
}

