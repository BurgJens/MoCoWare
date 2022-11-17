package de.testjens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import de.testjens.view.screens.ScreenBeitreten
import de.testjens.view.screens.ScreenStart

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                ScreenBeitreten(mutableListOf("1","2","3"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}