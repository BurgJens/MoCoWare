package de.mocoware.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.mocoware.model.MiniGameEnum
import de.mocoware.model.PlayedGamesDataStore
import de.mocoware.view.elements.ButtonStandard
import de.mocoware.viewmodel.StatisticsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ScreenStatistics(
    viewModel: StatisticsViewModel
) {
    val gameStats by viewModel.gameStatsLive.observeAsState()
    val playerName by viewModel.playerNameLive.observeAsState()

    val firstDraw = remember{mutableStateOf(true)}

    val thisContext = LocalContext.current

    if (firstDraw.value){
        firstDraw.value = false
        viewModel.updatePlayerName(thisContext)
        viewModel.updateGameStats(thisContext)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Playerstatistics of"
        )
        Text(
            text = playerName ?: "undefined",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp
        )
        
        LazyColumn {
            itemsIndexed(gameStats ?: mutableListOf()) { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center
                ) {
                    Text(
                        text = item.first,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 1.dp, vertical = 8.dp)
                    )
                    Text(
                        text = item.second.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(horizontal = 1.dp, vertical = 8.dp)
                    )
                    Text(
                        text = item.third.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.3f)
                            .padding(horizontal = 1.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}