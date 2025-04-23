package com.necdetzr.notiflax2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.ui.components.BottomNavBar
import com.necdetzr.notiflax2.ui.screens.main.AddScreen
import com.necdetzr.notiflax2.ui.screens.main.HomeScreen
import com.necdetzr.notiflax2.ui.screens.main.ProfileScreen
import com.necdetzr.notiflax2.ui.screens.main.pages.RemindersPage


@Composable

fun MainScreen() {
    val auth = FirebaseAuth.getInstance()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("add") { AddScreen(navController) }
            composable("profile") { ProfileScreen(navController = navController, auth = auth) }
            composable("reminders"){
                RemindersPage(navController = navController)
            }
            }
        }
    }

