package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.ui.theme.AppTypography


@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    containerColor: Long,
    contentColor:Long,
    modifier: Modifier = Modifier
){
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(containerColor),
            contentColor = Color(contentColor)
        ),
        modifier = modifier
    ) {
        Text(text = text,style = AppTypography.labelLarge)

    }
}