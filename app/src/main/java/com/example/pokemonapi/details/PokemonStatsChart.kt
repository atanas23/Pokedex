package com.example.pokemonapi.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokemonapi.datamodels.PokemonStat
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.common.data.ExtraStore
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer

private val labelListKey = ExtraStore.Key<List<String>>() 

@Composable
fun PokemonStatsChart(stats: List<PokemonStat>, barColor: Color) {
    val modelProducer = remember { CartesianChartModelProducer.build {  } }

    //map stat names to stat values
    val statNameToBaseStatMap = stats.map { it.stat.name.replaceFirstChar { it.uppercase() } }
        .zip(stats.map { it.base_stat })
        .toMap()

    Column {
        Text(
            text = "Stats",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            LaunchedEffect(stats) {
                withContext(Dispatchers.Default) {
                    modelProducer.runTransaction {
                        columnSeries {
                            series(
                                *stats.map { it.base_stat.toFloat() }.toTypedArray()
                            )
                        }
                        updateExtras { it[labelListKey] = statNameToBaseStatMap.keys.toList() }
                    }
                }
            }

            CartesianChartHost(
                chart = rememberCartesianChart(
                    layers = arrayOf(
                        rememberColumnCartesianLayer(
                            ColumnCartesianLayer.ColumnProvider.series(
                                rememberLineComponent(
                                    color = barColor,
                                    thickness = 16.dp,
                                )
                            )
                        )
                    ),
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = CartesianValueFormatter { x, value, _ ->
                            try {
                                value.model.extraStore[labelListKey][x.toInt()]
                            } catch (e: Exception) {
                                Log.d("error", e.message.toString())
                                return@CartesianValueFormatter ""
                            }
                        }
                    )
                ),
                modelProducer = modelProducer,
            )
        }
    }
}


