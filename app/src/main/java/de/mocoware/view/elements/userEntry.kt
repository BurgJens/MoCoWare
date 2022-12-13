package de.mocoware.view.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.mocoware.R


@Composable
fun UserEntry(name: String, rounds: Int){
    Row(){
        Image(
            painterResource(R.drawable.ic_baseline_account_box_24),
            "bla",
            Modifier
                .weight(1f)
                .fillMaxHeight()
        )
        Column() {
            Text(text = name)
            Row() {
                repeat(rounds) {
                    Image(painterResource(R.drawable.ic_baseline_square_24), "odgjodgj")
                }
            }
        }
    }
}