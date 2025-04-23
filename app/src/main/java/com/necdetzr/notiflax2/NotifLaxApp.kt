package com.necdetzr.notiflax2

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.di.AppContainer
import com.necdetzr.notiflax2.navigation.ApplicationNav
import com.necdetzr.notiflax2.viewmodel.AuthViewModel

@Composable
fun NotifLaxApp(
    authViewModel: AuthViewModel = viewModel(factory = AppContainer(Application()).authViewModelFactory)
){
    val auth = FirebaseAuth.getInstance()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            ApplicationNav(auth = auth, authViewModel = authViewModel,it )

        }

    }
    

}