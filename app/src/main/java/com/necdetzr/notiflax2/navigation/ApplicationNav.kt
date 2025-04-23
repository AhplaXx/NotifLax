package com.necdetzr.notiflax2.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.ui.MainScreen
import com.necdetzr.notiflax2.ui.screens.auth.SecondWelcomePage
import com.necdetzr.notiflax2.ui.screens.auth.SignInPage
import com.necdetzr.notiflax2.ui.screens.auth.SignUpPage
import com.necdetzr.notiflax2.ui.screens.auth.WelcomePage
import com.necdetzr.notiflax2.ui.screens.main.AddScreen
import com.necdetzr.notiflax2.ui.screens.main.HomeScreen
import com.necdetzr.notiflax2.ui.screens.main.ProfileScreen
import com.necdetzr.notiflax2.viewmodel.AuthViewModel


@Composable
fun ApplicationNav(auth:FirebaseAuth,authViewModel: AuthViewModel,paddingValues: PaddingValues) {
    val navController = rememberNavController()
    val startDest = if (auth.currentUser != null) "main" else "auth"

    NavHost(navController = navController, startDestination = startDest) {
        navigation(startDestination = "second_welcome", route = "auth") {

            composable("second_welcome"){
                SecondWelcomePage(navController = navController)
            }
            composable("login") {
                SignInPage(navController, authViewModel)
            }
            composable("register") {
                SignUpPage(navController, authViewModel)
            }
        }

        composable("main") {
            MainScreen()

        }
    }
}