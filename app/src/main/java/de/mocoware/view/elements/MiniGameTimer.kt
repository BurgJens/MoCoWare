package de.mocoware.view.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData

@Composable
fun MiniGameTimer(time : LiveData<Int>){
    val time by time.observeAsState()
    Card(
        modifier = Modifier.
        height(80.dp).
        width(80.dp),
//        backgroundColor = Color.Transparent
    ) {
        Text(
            text = "$time",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 64.sp,
            textAlign = TextAlign.Center
        )
    }
}