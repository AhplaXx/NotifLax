package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.ui.theme.AppTypography


@Composable
fun OrDivider(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Divider(
            modifier = Modifier.width(160.dp)
        )
        Text(text = "Or", color = Color.LightGray, style = AppTypography.bodyMedium)
        Divider(modifier = Modifier.width(160.dp))
    }


}