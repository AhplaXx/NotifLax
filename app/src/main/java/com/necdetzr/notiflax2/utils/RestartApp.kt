package com.necdetzr.notiflax2.utils

import android.app.Activity
import android.content.Context
import android.content.Intent


fun restartApp(context:Context){
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
    if (context is Activity){
        context.finish()
    }

}