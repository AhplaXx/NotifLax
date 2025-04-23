package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


@Composable
fun OAuthButton(
    onClick: ()-> Unit,
    logo:Painter,
    description:String,
    containerColor:Long,
    modifier: Modifier


){
    Button(onClick =
    { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(containerColor),
        ),
        modifier = modifier
    ) {
        Icon(
            painter =logo ,
            contentDescription =description ,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxSize()
        )

    }
}