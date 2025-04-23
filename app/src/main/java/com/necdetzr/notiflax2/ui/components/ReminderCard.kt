package com.necdetzr.notiflax2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.necdetzr.notiflax2.ui.theme.AppTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReminderCard(
    category: String,
    title: String,
    time:String,
    onDelete: ()->Unit,
){
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                onDelete()

                true
            } else {
                false
            }
        }
    )
    var checked by remember{ mutableStateOf(false) }
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        background ={
            Box(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .background(Color(0xFFEEEEEE))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                shape = RoundedCornerShape(8.dp),


                ) {
                Row(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxHeight()
                            .size(51.dp)
                            .background(Color(0xFF00ADB5))
                    ) {
                        Text(
                            text = category,
                            modifier = Modifier.graphicsLayer(rotationZ = 270f),
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = title, style = AppTypography.titleSmall)
                        Row {
                            Icon(imageVector = Icons.Outlined.Timer, contentDescription = "time")
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = time)


                        }

                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = if (checked) Color.Green else Color.LightGray,
                                shape = CircleShape
                            )
                            .clickable { checked = !checked },
                        contentAlignment = Alignment.Center
                    ) {
                        if (checked) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Round Checkbox",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }


                    }
                }
            }
        }
    )

    }
