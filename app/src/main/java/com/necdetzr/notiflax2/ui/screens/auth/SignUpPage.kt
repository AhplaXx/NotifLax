package com.necdetzr.notiflax2.ui.screens.auth

import CustomPasswordTextField
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.components.CustomButton
import com.necdetzr.notiflax2.ui.components.CustomSnackBar
import com.necdetzr.notiflax2.ui.components.CustomTextField
import com.necdetzr.notiflax2.ui.components.LinearProgressBar
import com.necdetzr.notiflax2.ui.components.OrDivider
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpPage(
    navController: NavHostController,
    viewModel: AuthViewModel
){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var password2 by remember {
        mutableStateOf("")
    }
    var rememberMe by remember {
        mutableStateOf(false)
    }
    var loginError by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(user) {
        if(user!= null){
            navController.navigate("main"){
                popUpTo("signUp"){
                    inclusive = true
                }
            }
        }

    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 120.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_removebg_preview),
            contentDescription = "Logo_Auth",
            modifier = Modifier.size(180.dp)
        )

            CustomTextField(icon = Icons.Default.Email, value =email , onValueChange = {email = it}, containerColor = 0xFFF8F8F8, modifier = Modifier.border(1.dp,Color(0xFFD6D6D6),
                RoundedCornerShape(8.dp),

            ),
                placeholder = "example@gmail.com",
                title = R.string.email
                )

        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(text = stringResource(id = R.string.password), color = Color.LightGray, style = AppTypography.bodySmall ,modifier = Modifier.align(Alignment.Start))
            CustomPasswordTextField(icon = Icons.Default.Password, value =password , onValueChange = {password = it}, containerColor = 0xFFF8F8F8, modifier = Modifier.border(1.dp,Color(0xFFD6D6D6),
                RoundedCornerShape(8.dp),

                ),
                placeholder = "********"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(text = stringResource(id = R.string.password_again), color = Color.LightGray, style = AppTypography.bodySmall ,modifier = Modifier.align(
                Alignment.Start))
            CustomPasswordTextField(icon = Icons.Default.Password, value =password2 , onValueChange = {password2 = it}, containerColor = 0xFFF8F8F8, modifier = Modifier.border(1.dp,
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
                Checkbox(checked = rememberMe, onCheckedChange = {rememberMe = it})
                Text(text = stringResource(id = R.string.remember_me), color = Color.Gray, style = AppTypography.bodySmall)
            }
            Spacer(modifier = Modifier.width(60.dp))
            TextButton(
                onClick = {
                    navController.navigate("signIn"){
                        popUpTo("signUp"){
                            inclusive = true
                        }
                    }

                },
            ){
               Text( text = stringResource(id = R.string.have_account), color = Color.Gray, style = AppTypography.bodySmall)

            }



        }
        Spacer(modifier = Modifier.height(40.dp))
        if(isLoading){
            LinearProgressBar()
        }else{
            CustomButton(
                onClick = {
                    if(password!=password2){
                        scope.launch {
                            snackbarHostState.showSnackbar("Password's doesn't match")
                        }

                    }else{
                        isLoading = true
                        viewModel.register(email,password){
                                result-> isLoading = false
                            result.onSuccess {response->
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
                                    snackbarHostState.showSnackbar("errorMessage")
                                }
                            }


                        }
                    }


                },
                text = stringResource(id = R.string.signUp_button) ,
                containerColor =0xFF00ADB5 ,
                contentColor =0xFFEEEEEE,
                modifier = Modifier.size(width = 250.dp, height = 70.dp)
            )
        }
        SnackbarHost(hostState = snackbarHostState, snackbar = {snackbarData -> CustomSnackBar(snackbarData)})


        Spacer(modifier = Modifier.height(40.dp))
        OrDivider()

    }
    





}