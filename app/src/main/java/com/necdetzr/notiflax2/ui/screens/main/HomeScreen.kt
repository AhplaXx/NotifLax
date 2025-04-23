package com.necdetzr.notiflax2.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.data.model.ReminderModel
import com.necdetzr.notiflax2.ui.components.LinearProgressBar
import com.necdetzr.notiflax2.ui.components.ReminderCard
import com.necdetzr.notiflax2.ui.components.UpComingDaysRow
import com.necdetzr.notiflax2.ui.theme.AppTypography
import com.necdetzr.notiflax2.viewmodel.ReminderViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


@Composable
fun HomeScreen(viewModel:ReminderViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val reminders by viewModel.reminders.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()
    val locale = when (Locale.getDefault().language) {
        "tr" -> Locale("tr", "TR")
        else -> Locale("en", "US")
    }

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale)

    val today = LocalDate.now().format(formatter)

    val todayReminders = reminders.filter { it.date == today }
    val remindersLength = todayReminders.size

    LaunchedEffect(currentUserId) {
        viewModel.fetchReminders(currentUserId)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenHeightDp = LocalConfiguration.current.screenHeightDp

        Box(
            modifier = Modifier
                .height((screenHeightDp / 2.4).dp)
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(
                    Color(0xFF00ADB5)
                )
                .fillMaxSize(),


        ){
            Column(
                modifier = Modifier.fillMaxSize()

            ) {
                Row(
                    modifier = Modifier.padding(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(


                    ) {
                        Text(text = stringResource(id = R.string.welcome_back), style = AppTypography.titleMedium, color = Color.White, modifier = Modifier.width(140.dp))

                        Spacer(modifier = Modifier.height(20.dp))


                        Text(text = stringResource(id = R.string.whats_plan), style = AppTypography.titleMedium, color = Color.White,modifier = Modifier.width(170.dp))


                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "calendar",
                        modifier = Modifier
                            .size(120.dp)

                    )

                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UpComingDaysRow()

                }


            }




        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.todays_plans), style = AppTypography.titleMedium)
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "($remindersLength)", style = AppTypography.labelLarge, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(10.dp))
            if(isLoading){
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    LinearProgressBar()

                }
            }else{
                if(todayReminders.isEmpty()){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(painter = painterResource(id = R.drawable.sad_notification), contentDescription ="sad_notification" )
                        Text(text = stringResource(id = R.string.no_reminder), style = AppTypography.bodyMedium, color = Color.Gray)
                    }
                }
                LazyColumn {

                    items(todayReminders) { reminder ->
                        ReminderCard(
                            reminder.category,
                            reminder.title,
                            reminder.time
                        ) { viewModel.deleteReminder(reminder.id,reminder.userId) }
                    }
                }
            }
            

        }
    }
}