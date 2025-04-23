package com.necdetzr.notiflax2.ui.screens.main

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DoorBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.utils.restartApp
import com.necdetzr.notiflax2.viewmodel.SettingsViewModel


@Composable
fun ProfileScreen(auth:FirebaseAuth,navController:NavHostController,viewModel:SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val email = auth.currentUser?.email
    var expandedLanguage by remember { mutableStateOf(false) }
    val context = LocalContext.current




    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Person, contentDescription ="person_logo")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = email.toString(), style = AppTypography.titleSmall)

        }
        Spacer(modifier = Modifier.height(52.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = stringResource(id = R.string.general), color = Color.Gray, style = AppTypography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        navController.navigate("reminders")
                    }
                ) {
                    Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription ="calendar" )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.recent_reminders), style = AppTypography.bodyMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow_forward")

                }

            }
            Spacer(modifier = Modifier.height(4.dp))
            Column {
                Text(text = stringResource(id = R.string.account_settings), color = Color.Gray, style = AppTypography.bodyMedium)
                //Spacer(modifier = Modifier.height(20.dp))
                /*
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.DarkMode, contentDescription ="calendar" )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.dark_mode), style = AppTypography.bodyMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Switch(
                        checked = false,
                        onCheckedChange = {},
                        colors = androidx.compose.material3.SwitchDefaults.colors(
                            checkedThumbColor = Color.Black,
                            checkedTrackColor = Color.White,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.Black
                        ),
                        modifier = Modifier.graphicsLayer(
                            scaleX = 0.8f,
                            scaleY = 0.8f
                        )

                    )

                }*/
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        if(context is Activity){
                            auth.signOut()

                            context.finish()

                        }


                    }
                ) {
                    Icon(imageVector = Icons.Default.DoorBack, contentDescription ="calendar" )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.log_out), style = AppTypography.bodyMedium, color = Color.Red)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow_forward")

                }

            }
            Spacer(modifier = Modifier.height(4.dp))
            Column {
                Text(text = stringResource(id = R.string.app_settings), color = Color.Gray, style = AppTypography.bodyMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        expandedLanguage = !expandedLanguage
                    }
                ) {
                    Icon(imageVector = Icons.Default.Translate, contentDescription ="calendar" )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.language), style = AppTypography.bodyMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow_forward")
                    DropdownMenu(

                        expanded = expandedLanguage,
                        onDismissRequest = { expandedLanguage = false },

                    ) {
                        DropdownMenuItem(text = { Text(text = "Türkçe") }, onClick = {
                            viewModel.setLanguage("tr")
                            restartApp(context = context)
                            })
                        DropdownMenuItem(text = { Text(text = "English") }, onClick = {
                            viewModel.setLanguage("en")
                            restartApp(context)
                             })
                        
                    }

                }
                /*Spacer(modifier = Modifier.height(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription ="calendar" )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.notification), style = AppTypography.bodyMedium)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow_forward")

                }*/

            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        



    }
}