package com.necdetzr.notiflax2.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.components.CustomButton
import com.necdetzr.notiflax2.ui.theme.AppTypography


@Composable
fun SecondWelcomePage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(painter = painterResource(id = R.drawable.image_removebg_preview), contentDescription ="Welcome Logo", modifier = Modifier.size(150.dp) )
        Text(text = stringResource(id = R.string.second_welcome_title), style = AppTypography.titleLarge)
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = stringResource(id = R.string.second_welcome_text), style = AppTypography.labelMedium, modifier = Modifier.width(200.dp))
        Spacer(modifier = Modifier.height(70.dp))
        CustomButton(
            onClick = {
                navController.navigate("register"){
                    popUpTo("second_welcome"){
                        inclusive = true
                    }
                }
            },
            text = stringResource(id = R.string.signUp_button) , 
            containerColor =0xFF00ADB5 ,
            contentColor = 0xFFEEEEEE,
            modifier = Modifier.size(width = 250.dp, height = 70.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(
            onClick = {
                navController.navigate("login"){
                    popUpTo("second_welcome"){
                        inclusive = true
                    }
                }
            },
            text = stringResource(id = R.string.signIn_button) ,
            containerColor =0xFFEEEEEE,
            contentColor = 0xFF00ADB5,
            modifier = Modifier.size(width = 250.dp, height = 70.dp)
        )




    }
}