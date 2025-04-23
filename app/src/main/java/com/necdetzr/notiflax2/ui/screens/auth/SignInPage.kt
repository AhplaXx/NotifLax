package com.necdetzr.notiflax2.ui.screens.auth

import CustomPasswordTextField
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.components.CustomButton
import com.necdetzr.notiflax2.ui.components.CustomSnackBar
import com.necdetzr.notiflax2.ui.components.CustomTextField
import com.necdetzr.notiflax2.ui.components.LinearProgressBar
import com.necdetzr.notiflax2.ui.components.OAuthButton
import com.necdetzr.notiflax2.ui.components.OrDivider
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.viewmodel.AuthViewModel
import kotlinx.coroutines.launch



@Composable
fun SignInPage(
    navController: NavHostController,
    viewModel: AuthViewModel
){

    val context = LocalContext.current
    val sharedPreferences : SharedPreferences = context.getSharedPreferences("user_preferences",
        Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val savedEmail = sharedPreferences.getString("email", "")
    val savedPassword = sharedPreferences.getString("password", "")
    val savedRememberMe = sharedPreferences.getBoolean("rememberMe", false)
    val errorMessage = context.getString(R.string.invalid_login)

    var email by remember {
        mutableStateOf(savedEmail ?:"" )
    }
    var password by remember {
        mutableStateOf(savedPassword?:"")
    }
    var rememberMe by remember {
        mutableStateOf(savedRememberMe)
    }
    var isLoadings by remember{
        mutableStateOf(false)
    }
    var loginError by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_removebg_preview),
            contentDescription = "Logo_Auth",
            modifier = Modifier.size(180.dp)
        )


            CustomTextField(icon = Icons.Default.Email, value =email , onValueChange = {email = it}, containerColor = 0xFFF8F8F8, modifier = Modifier.border(1.dp,
                Color(0xFFD6D6D6),
                RoundedCornerShape(8.dp),

                ),
                placeholder = "example@gmail.com",
                title = R.string.email
            )

        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(text = stringResource(id = R.string.password), color = Color.LightGray, style = AppTypography.bodySmall ,modifier = Modifier.align(
                Alignment.Start))
            CustomPasswordTextField(icon = Icons.Default.Password, value =password , onValueChange = {password = it}, containerColor = 0xFFF8F8F8, modifier = Modifier.border(1.dp,
                Color(0xFFD6D6D6),
                RoundedCornerShape(8.dp),

                ),
                placeholder = "********"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = {rememberMe = it}
                )
                Text(text = stringResource(id = R.string.remember_me), color = Color.Gray, style = AppTypography.bodySmall)
            }
            Spacer(modifier = Modifier.width(60.dp))
            TextButton(
                onClick = {
                    navController.navigate("signUp"){
                        popUpTo("signIn"){
                            inclusive = true
                        }
                    }

                },
            ){
                Text( text = stringResource(id = R.string.dont_account), color = Color.Gray, style = AppTypography.bodySmall)

            }



        }
        Spacer(modifier = Modifier.height(40.dp))
        if(isLoadings){
            LinearProgressBar()

        }else{
            CustomButton(
                onClick = {
                    isLoadings = true
                    viewModel.login(email,password){

                        result-> isLoadings = false
                        result.onSuccess {response->
                            if (rememberMe){
                                editor.putBoolean("rememberMe",rememberMe)
                                editor.putString("email",email)
                                editor.putString("password",password)
                                editor.apply()

                            }else{
                                editor.clear()
                                editor.apply()
                            }
                            loginError = null
                            navController.navigate("main"){
                                popUpTo("signIn"){
                                    inclusive = true
                                }

                            }


                        }
                        result.onFailure {
                            loginError = it.message
                            println(loginError)
                            scope.launch {
                                snackbarHostState.showSnackbar(errorMessage)
                            }
                        }


                    }

                },
                text = stringResource(id = R.string.signIn_button) ,
                containerColor =0xFFEEEEEE ,
                contentColor =0xFF00ADB5,
                modifier = Modifier.size(width = 250.dp, height = 70.dp)
            )
        }
            SnackbarHost(hostState = snackbarHostState, snackbar = {snackbarData -> CustomSnackBar(
                snackbarData = snackbarData
            ) })

        Spacer(modifier = Modifier.height(40.dp))
        OrDivider()
        Spacer(modifier = Modifier.height(40.dp))
        OAuthButton(
            onClick = { /*TODO*/ },
            logo = painterResource(id = R.drawable.google) ,
            description = "google_logo",
            containerColor = 0xFFEEEEEE,

            modifier = Modifier.size(90.dp)
        )

    }
}