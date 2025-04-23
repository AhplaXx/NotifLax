package com.necdetzr.notiflax2.notification

import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.impl.WorkManagerImpl
import androidx.work.workDataOf
import com.necdetzr.notiflax2.data.model.ReminderModel
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class NotificationHelper{

    fun scheduleReminderNotification(context:Context,reminder: ReminderModel){
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

        val dateTimeString = "${reminder.date} ${reminder.time}"
        val reminderTime = LocalDateTime.parse(dateTimeString, formatter)
        val delayMillis = Duration.between(LocalDateTime.now(),reminderTime).toMillis()
        Log.d("NotificationHelper", "Reminder alınan tarih: $dateTimeString")
        Log.d("NotificationHelper", "delayMillis: $delayMillis")

        if(delayMillis > 0){
            val data = workDataOf(
                "title" to reminder.category,
                "message" to reminder.title,
                "date" to reminder.date,
                "time" to reminder.time
            )
            val request = OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}