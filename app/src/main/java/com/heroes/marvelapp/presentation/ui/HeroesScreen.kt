package com.heroes.marvelapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    onSelectItem: (CharactersMarvel) -> Unit
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

        Box(modifier = Modifier.weight(0.9f)) {
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
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(charactersState.heroes) { character ->
                            ComicItem(character) {
                                //viewModel.characterSelected.value = character
                                onSelectItem(character)
                            }
                        }
                    }
                }

                charactersState.error != null -> {
                    Text(text = "Error in the service")
                }
            }
        }
    }
}

@Composable
fun ComicItem(
    character: CharactersMarvel,
    onSelectItem: () -> Unit
) {
    Column {
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
            AsyncImage(
                model = character.thumbnail.path.plus(".").plus(character.thumbnail.extension)
                    .replace("http", "https"),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = character.name,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarvelGridScreen() {
    MarvelGridScreen {}
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
    ) {}
}

