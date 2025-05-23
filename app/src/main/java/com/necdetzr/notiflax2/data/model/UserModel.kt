package com.necdetzr.notiflax2.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(
    val email:String,
    val password:String,
) : Parcelable