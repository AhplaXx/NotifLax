package com.necdetzr.notiflax2.ui.components

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun TimePickerComponent(
    selectedTime: String,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            val formattedTime = String.format("%02d:%02d", hour, minute)
            onTimeSelected(formattedTime)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    Button(onClick = { timePickerDialog.show() }) {
        Text(text = selectedTime.ifEmpty { "Saat Seç" })
    }
}
