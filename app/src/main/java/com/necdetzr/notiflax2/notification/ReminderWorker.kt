package com.necdetzr.notiflax2.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.necdetzr.notiflax2.R
import kotlin.random.Random

class ReminderWorker(context:Context,params:WorkerParameters): Worker(context,params){
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Reminder"
        val message = inputData.getString("message") ?: "Message"
        val date = inputData.getString("date") ?: "Date"
        val time = inputData.getString("time") ?: "Time"
        Log.d("ReminderWorker", "Bildirim gösteriliyor: $title - $message")

        if(auth.currentUser != null){
            showNotification(applicationContext,title,message,date,time)

        }else{
            Log.d("ReminderWorker", "User is not authenticated")

        }

        return Result.success()
    }

    @SuppressLint("MissingPermission")
    fun showNotification(context: Context, title:String, message:String,date:String,time:String){
        val channelId = "reminder_channel"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId,"Reminder Notifications",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(R.drawable.image_removebg_preview_small)
            .setContentTitle(title)
            .setContentText("$message $date $time")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(context).notify(Random.nextInt(),notification)

    }

}