package com.example.pokemonapi.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.pokemonapi.datamodels.PokemonStat
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.common.data.ExtraStore

private val labelListKey = ExtraStore.Key<List<String>>() 

@Composable
fun PokemonStatsChart(stats: List<PokemonStat>) {
    val modelProducer = remember { CartesianChartModelProducer.build {  } }

    //map stat names to stat values
    val statNameToBaseStatMap = stats.map { it.stat.name.replaceFirstChar { it.uppercase() } }
        .zip(stats.map { it.base_stat })
        .toMap()

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
            layers = arrayOf(rememberColumnCartesianLayer()),
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

