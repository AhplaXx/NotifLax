package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.R
import com.necdetzr.notiflax2.ui.theme.AppTypography

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit,
    containerColor: Long,
    placeholder: String = "Enter text...",
    title: Int
) {
    Column {
        androidx.compose.material.Text(
            text = stringResource(id = title),
            color = Color.LightGray,
            style = AppTypography.bodySmall,
            modifier = Modifier.align(
                Alignment.Start
            )
        )
        TextField(
            placeholder = { Text(text = placeholder, color = Color.LightGray) },
            leadingIcon = { Icon(imageVector = icon, contentDescription = "email", tint = Color.LightGray) },
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),

            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(containerColor),
                unfocusedContainerColor = Color(containerColor),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray,
                focusedTextColor = Color.Black,

                ),

            )

    }


}