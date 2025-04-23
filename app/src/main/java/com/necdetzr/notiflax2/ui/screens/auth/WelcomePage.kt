package com.necdetzr.notiflax2.ui.screens.auth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.components.CustomButton
import com.necdetzr.notiflax2.ui.theme.AppTypography

@Composable
fun WelcomePage(
    navController: NavHostController
){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    Column(
        modifier = Modifier
            .background(Color(0xFF00ADB5))
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(text = stringResource(id =R.string.welcome_title),style = AppTypography.titleLarge, color = Color.White)
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id = R.drawable.task_management), contentDescription ="Task Management", contentScale = ContentScale.Crop, modifier = Modifier.size(300.dp))
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = stringResource(id = R.string.welcome_text), style = AppTypography.bodyMedium, color = Color.White, modifier = Modifier.width(200.dp))
        Spacer(modifier = Modifier.height(70.dp))

        CustomButton(
            onClick = {
                editor.putBoolean("isFirstLaunch",false)
                editor.apply()
                navController.navigate("second_welcome"){
                    popUpTo("first_welcome"){
                        inclusive = true
                    }
                }
                      },
            text = stringResource(id = R.string.continue_button),
            containerColor = 0xFFEEEEEE,
            contentColor =0xFF00ADB5,
            modifier = Modifier.size(width = 250.dp, height = 70.dp)

        )

    }

}