package de.testjens

import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import de.testjens.ui.theme.MoCoWareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoCoWareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UserEntry("Android", 10)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun UserEntry(name: String, rounds: Int){
    Row(){
        Image(painterResource(R.drawable.ic_baseline_account_box_24),
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoCoWareTheme {
        UserEntry("Jürgen",10)
    }
}