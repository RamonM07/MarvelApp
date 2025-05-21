package com.heroes.marvelapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.heroes.marvelapp.R
import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel
import com.heroes.marvelapp.domain.model.getCharacters.Thumbnail
import com.heroes.marvelapp.presentation.viewModel.CharactersViewModel


@Composable
fun MarvelGridScreen(
    modifier: Modifier = Modifier,
    viewModel: CharactersViewModel = hiltViewModel(),
    onSelectItem : () -> Unit
) {
    val charactersState by viewModel.charactersState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f),
            painter = painterResource(R.drawable.ic_marvel),
            contentDescription = ""
        )

        Box(modifier = Modifier.weight(0.8f)){
            when {
                charactersState.isLoading -> {
                    CircularProgressIndicator(
                        trackColor = MaterialTheme.colorScheme.primary
                    )
                }

                charactersState.heroes.isNotEmpty() -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(charactersState.heroes) { character ->
                            ComicItem(character){
                                onSelectItem()
                            }
                        }
                    }
                }

                charactersState.error != null -> {
                    Text(text = "Error in the service")
                }
            }
        }

        Spacer(Modifier.weight(0.1f))

        Text(
            text = "Marvel",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun ComicItem(
    character: CharactersMarvel,
    onSelectItem : () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onSelectItem
    ) {
        Log.d("Image path", character.thumbnail.path.plus(".").plus(character.thumbnail.extension))
        Row {
            AsyncImage(
                model = character.thumbnail.path.plus(".").plus(character.thumbnail.extension),
                contentDescription = "",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarvelGridScreen() {
    MarvelGridScreen{}
}

@Preview
@Composable
private fun ItemComicPrev() {
    ComicItem(
        CharactersMarvel(
            "",
            0,
            "",
            "",
            "",
            Thumbnail("", "")
        )
    ){}
}

