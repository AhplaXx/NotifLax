package com.necdetzr.notiflax2.ui.components

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(
    selectedDate: String,
    selectedTime: String,
    onDateSelected: (String) -> Unit,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val openDateDialog = remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    val calendar = Calendar.getInstance()

    // Saat picker
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

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Button(onClick = { openDateDialog.value = true },shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF00ADB5),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )) {
            Text(text = selectedDate.ifEmpty { stringResource(id = R.string.pick_date) })
        }
        Spacer(modifier = Modifier.width(10.dp))

        Button(onClick = { timePickerDialog.show() },shape = RoundedCornerShape(4.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF00ADB5),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )) {
            Text(text = selectedTime.ifEmpty { stringResource(id = R.string.time) })
        }

        if (openDateDialog.value) {
            DatePickerDialog(
                onDismissRequest = { openDateDialog.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        openDateDialog.value = false
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        selectedDateMillis?.let {
                            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                .format(Date(it))
                            onDateSelected(formattedDate)
                        }
                    },

                        ) {
                        Text(stringResource(id = R.string.ok))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

