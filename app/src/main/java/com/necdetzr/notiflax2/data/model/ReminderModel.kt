package com.necdetzr.notiflax2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ReminderModel(
    val id:String = "",
    val title: String = "",
    val date: String = "",
    val time: String = "",
    val color: Long = 0,
    val category: String = "",
    val notification: Boolean = false,
    val userId: String = ""
) : Parcelable