package com.necdetzr.notiflax2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem (
    val title:String,
    val icon: ImageVector,
    val route:String

)
val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Outlined.Home, "home"),
    BottomNavItem("Add",Icons.Outlined.Add,"add"),
    BottomNavItem("Profile",Icons.Outlined.Person,"profile")

)