package com.example.pokemonapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

class MainActivity : ComponentActivity() {
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pokemonDataList by viewModel.pokemonDataList.observeAsState(emptyList())
            val errorMessage by viewModel.errorMessage.observeAsState()

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                PokemonScreen(
                    pokemons = pokemonDataList,
                    errorMessage = errorMessage,
                    onFetchPokemon = {
                        viewModel.getPokemon(1)
                    }
                )
            }
        }

        viewModel.getPokemon(1)
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun PokemonItem(pokemon: Pokemon) {
    val backgroundColor = getBackgroundColor(pokemon.types.firstOrNull()?.type?.name)

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(4.dp)
                .background(color = backgroundColor, shape = MaterialTheme.shapes.medium)
                .padding(4.dp),
            verticalAlignment = Alignment.Top
        ) {
            PokemonID(pokemon.id)
            PokemonDetails(pokemon)
            Spacer(modifier = Modifier.weight(1f))
            PokemonImage(pokemon.sprites.front_default)
        }
    }
}

@Composable
fun getBackgroundColor(typeName: String?): Color {
    return when (typeName) {
        "normal" -> Color(0xFFA8A878)
        "poison" -> Color(0xFFA040A0)
        "psychic" -> Color(0xFFF85888)
        "grass" -> Color(0xFF78C850)
        "ground" -> Color(0xFFE0C068)
        "ice" -> Color(0xFF98D8D8)
        "fire" -> Color(0xFFF08030)
        "rock" -> Color(0xFFB8A038)
        "dragon" -> Color(0xFF7038F8)
        "water" -> Color(0xFF6890F0)
        "bug" -> Color(0xFFA8B820)
        "dark" -> Color(0xFF705848)
        "fighting" -> Color(0xFFC03028)
        "ghost" -> Color(0xFF705898)
        "steel" -> Color(0xFFB8B8D0)
        "flying" -> Color(0xFFA890F0)
        "electric" -> Color(0xFFF8D030)
        "fairy" -> Color(0xFFEE99AC)
        else -> Color(0xFFD3D3D3)
    }
}

@Composable
fun PokemonID(id: Int) {
    Text(
        text = "#${id.toString().padStart(3, '0')}",
        modifier = Modifier
            .wrapContentHeight(Alignment.Top)
            .width(50.dp)
    )
}

@Composable
fun PokemonDetails(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        TypeRow(pokemon.types)
    }
}

fun getDrawableResource(typeName: String): Int {
    return when (typeName) {
        "normal" -> R.drawable.normal
        "poison" -> R.drawable.poison
        "psychic" -> R.drawable.psychic
        "grass" -> R.drawable.grass
        "ground" -> R.drawable.ground
        "ice" -> R.drawable.ice
        "fire" -> R.drawable.fire
        "rock" -> R.drawable.rock
        "dragon" -> R.drawable.dragon
        "water" -> R.drawable.water
        "bug" -> R.drawable.bug
        "dark" -> R.drawable.dark
        "fighting" -> R.drawable.fighting
        "ghost" -> R.drawable.ghost
        "steel" -> R.drawable.steel
        "flying" -> R.drawable.flying
        "electric" -> R.drawable.electric
        "fairy" -> R.drawable.fairy
        else -> R.drawable.unknown 
    }
}

@Composable
fun TypeRow(types: List<PokemonType>) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        types.forEachIndexed { index, type ->
            if (index > 0){
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
            val pokemonType = type.type.name.uppercase()
            val drawableResId = getDrawableResource(type.type.name)
            AssistChip(
                onClick = { Log.d("assist chip", "test")},
                label = { Text(text = type.type.name.uppercase()) },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = drawableResId),
                        contentDescription = pokemonType,
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color.White
                )
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonImage(imageUrl: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Pokemon Image",
        modifier = Modifier
            .size(120.dp)
            .padding(2.dp),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun PokemonScreen(
    pokemons: List<Pokemon>,
    errorMessage: String?,
    onFetchPokemon: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon = pokemon)
        }
        item {
            errorMessage?.let {
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
