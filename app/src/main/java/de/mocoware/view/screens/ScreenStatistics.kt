package de.mocoware.view.screens

import android.content.Context
import androidx.compose.animation.core.Animatable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    val availableGames by viewModel.gameStatsLive.observeAsState()

    val firstDraw = remember{mutableStateOf(true)}

    val thisContext = LocalContext.current

    if (firstDraw.value){
        firstDraw.value = false
        viewModel.updateGameStats(thisContext)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
    ){
        LazyColumn {
            itemsIndexed(availableGames ?: mutableListOf()) { index, item ->
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

        ButtonStandard(
            text = "annoyingButtons",
            modifier = Modifier,
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    PlayedGamesDataStore.incMGplayedCount(thisContext, MiniGameEnum.MGannoyingButtons)
                    PlayedGamesDataStore.incMGwonCount(thisContext, MiniGameEnum.MGannoyingButtons)
                }
            }
        )

        ButtonStandard(
            text = "annoyingButtons",
            modifier = Modifier,
            onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    PlayedGamesDataStore.incMGplayedCount(thisContext, MiniGameEnum.MGconfusingButtons)
                    PlayedGamesDataStore.incMGwonCount(thisContext, MiniGameEnum.MGconfusingButtons)
                }
            }
        )


    }


}