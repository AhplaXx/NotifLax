package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.R

@Composable
fun NotificationSelector(
    isNotificationEnabled: Boolean,
    onSelectionChange: (Boolean) -> Unit
) {
    Column {
        Text(text = stringResource(id = R.string.notification), color = Color.LightGray)

        Row {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                RadioButton(
                    selected = isNotificationEnabled,
                    onClick = { onSelectionChange(true) }
                )
                Text(text = stringResource(id = R.string.yes))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = !isNotificationEnabled,
                    onClick = { onSelectionChange(false) }
                )
                Text(text = stringResource(id =R.string.no ) )
            }
        }
    }
}
