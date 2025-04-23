package com.necdetzr.notiflax2.ui.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.data.model.ReminderModel
import com.necdetzr.notiflax2.notification.NotificationHelper
import com.necdetzr.notiflax2.ui.components.CustomButton
import com.necdetzr.notiflax2.ui.components.CustomTextField
import com.necdetzr.notiflax2.ui.components.DateTimePickerComponent
import com.necdetzr.notiflax2.ui.components.NotificationSelector
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.viewmodel.ReminderViewModel


@Composable
fun AddScreen(navController: NavHostController){
    var title by remember{ mutableStateOf("")}
    val context = LocalContext.current
    var selectedTime by remember{ mutableStateOf("") }
    var category by remember{ mutableStateOf("") }
    var selectedDate by remember{ mutableStateOf("") }
    var notification by remember{ mutableStateOf(false) }
    val screenHeightDp = LocalConfiguration.current.screenWidthDp
    val viewModel: ReminderViewModel = viewModel()
    val isReminderAdded by viewModel.isReminderAdded.collectAsState()
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    LaunchedEffect(isReminderAdded) {
        if (isReminderAdded!!) {
            Toast.makeText(context, R.string.reminder_added, Toast.LENGTH_SHORT).show()
            title = ""
            category = ""
            selectedDate = ""
            selectedTime = ""
            notification = false
            viewModel.resetReminderAddedState()
            navController.navigate("home"){
                popUpTo("add"){
                    inclusive = true
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        TopAppBar(

            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = R.string.add_new_reminder))

                }
            },
            backgroundColor = Color(0xFF00ADB5),
            contentColor = Color(0xFFEEEEEE),
            modifier = Modifier
                .height(90.dp)
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.information), style = AppTypography.titleMedium)
            Spacer(modifier = Modifier.height(10.dp))

        }
        Column(
            modifier = Modifier.padding(horizontal = 40.dp)
        ) {
            CustomTextField(icon = Icons.Default.TextFields, value = title , onValueChange = {title = it}, containerColor =0xFFEEEEEE, placeholder = stringResource(
                id = R.string.do_math
            ), modifier = Modifier.fillMaxWidth(), title =R.string.title )
            Spacer(modifier = Modifier.height(14.dp))


                CustomTextField(
                    icon = Icons.Default.Category,
                    value = category,
                    onValueChange ={category = it} ,
                    containerColor = 0xFFEEEEEE,
                    title = R.string.category,
                    placeholder = stringResource(id = R.string.education),
                    modifier = Modifier.width((screenHeightDp).dp))

            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(text = stringResource(id = R.string.date), color = Color.LightGray)
                Row(
                    
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    DateTimePickerComponent(
                        selectedDate = selectedDate,
                        selectedTime = selectedTime,
                        onDateSelected = { selectedDate = it },
                        onTimeSelected = { selectedTime = it }

                    )
                    Spacer(modifier = Modifier.width(60.dp))


                }
                Spacer(modifier = Modifier.height(10.dp))
                NotificationSelector(isNotificationEnabled = notification, onSelectionChange = {notification = it})
            }
            Spacer(modifier = Modifier.height(60.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(
                    onClick = {
                        val newReminder = ReminderModel(
                            id = "",
                            title = title,
                            time = selectedTime,
                            category = category,
                            date = selectedDate,
                            notification = notification,
                            color = 0xFF00ADB5,
                            userId = currentUserId
                        )
                        println(currentUserId)
                        if(newReminder.title.isEmpty() || newReminder.time.isEmpty() || newReminder.category.isEmpty() || newReminder.date.isEmpty()){
                            Toast.makeText(context, R.string.fill_all_field, Toast.LENGTH_SHORT).show()
                        }else{

                            viewModel.addReminder(newReminder)


                            if(notification){
                                NotificationHelper().scheduleReminderNotification(context,newReminder)

                            }



                        }




                    },
                    text = stringResource(id = R.string.add_new_reminder),
                    containerColor = 0xFFEEEEEE,
                    contentColor = 0xFF00ADB5,
                    modifier = Modifier
                        .width(250.dp)
                        .height(70.dp)
                )
            }









        }

    }

}