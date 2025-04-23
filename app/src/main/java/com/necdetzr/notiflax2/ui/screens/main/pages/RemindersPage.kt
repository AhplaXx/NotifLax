package com.necdetzr.notiflax2.ui.screens.main.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.data.model.ReminderModel
import com.necdetzr.notiflax2.ui.components.LinearProgressBar
import com.necdetzr.notiflax2.ui.components.ReminderCard
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.viewmodel.ReminderViewModel


@Composable
fun RemindersPage(viewModel:ReminderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),navController: NavHostController){
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val reminders by viewModel.reminders.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val groupedReminders = reminders.groupBy { it.date }
        .toSortedMap(compareByDescending { it })
    val reminderSize = reminders.size


    LaunchedEffect(currentUserId) {
        viewModel.fetchReminders(currentUserId)
    }
    Column {
        TopAppBar(
            backgroundColor = Color(0xFF00ADB5),
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "back_arrow",
                        tint = Color.White
                    )

                }
            },
            modifier = Modifier
                .height(70.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),


            title = {
                Text(text = stringResource(id = R.string.recent_reminders), color = Color.White)

            })
        Spacer(modifier = Modifier.height(10.dp))
        if(isLoading){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(46.dp)
            ) {
                LinearProgressBar()

            }
        }else{
            if(reminders.isEmpty()){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(painter = painterResource(id = R.drawable.sad_notification), contentDescription = "sad_notification")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = stringResource(id = R.string.no_reminder_all), style = AppTypography.bodyMedium, color = Color.Gray)

                }

            }
            LazyColumn(
                modifier = Modifier.padding(horizontal =16.dp)
            ) {
                groupedReminders.forEach{(date,remindersOnDate)->
                    item {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = date, style = AppTypography.titleMedium)
                        }


                    }
                    items(remindersOnDate){reminder->
                        ReminderCard(
                            category = reminder.category,
                            title = reminder.title,
                            time =reminder.time,
                            onDelete = {viewModel.deleteReminder(reminder.id,reminder.userId)}
                        )
                    }

                }

            }
        }
    }








}