package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun UpComingDaysRow(){
    val today = LocalDate.now()
    val upcomingDays =List(5){index->today.plusDays(index.toLong())}

    LazyRow(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        items(upcomingDays){date->
            DayCard(date = date)

        }

    }


}