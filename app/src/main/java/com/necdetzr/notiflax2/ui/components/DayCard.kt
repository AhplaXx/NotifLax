package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.necdetzr.notiflax2.ui.theme.AppTypography
import java.time.LocalDate

@Composable
fun DayCard(date: LocalDate){
    val today = LocalDate.now()
    if (date == today){
        Card(
            modifier = Modifier
                .padding(8.dp)
                .width(50.dp)
                .height(100.dp),
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF393E46))


            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = date.dayOfMonth.toString(), style = AppTypography.labelSmall, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = date.dayOfWeek.toString().substring(0,3), style = AppTypography.bodyMedium, color = Color.White)
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = date.month.toString().substring(0,5), style = AppTypography.bodySmall, color = Color.Gray)
                GlowingDot()

            }

        }

    }else{
        Card(
            modifier = Modifier
                .padding(8.dp)
                .width(50.dp)
                .height(70.dp),
            shape = RoundedCornerShape(6.dp)

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = date.dayOfMonth.toString(), style = AppTypography.labelSmall, color = Color.Gray)
                Text(text = date.dayOfWeek.toString().substring(0,3), style = AppTypography.bodyMedium)
                Text(text = date.month.toString().substring(0,5), style = AppTypography.bodySmall, color = Color.Gray)

            }

        }
    }

}