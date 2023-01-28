package de.mocoware.view.screens.minigames

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.mocoware.viewmodel.GameViewModel


@Composable
fun ScreeenMGlaufenWithService(viewModel: GameViewModel,
                               navController: NavController,
                               context: Context,

                               ){



    Text(modifier = Modifier.padding(20.dp), text ="Text test", color = Color.Red)



}