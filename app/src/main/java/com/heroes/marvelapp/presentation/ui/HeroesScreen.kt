package com.heroes.marvelapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.heroes.marvelapp.presentation.theme.Purple40


@Composable
fun MarvelGridScreen(modifier : Modifier = Modifier) {
    val comics = List(6) {
        Comic(
            title = "Name",
            imageUrl = "https://upload.wikimedia.org/wikipedia/en/3/35/X-Men_Vol_5_1.png"
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Marvel App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f).background(Purple40),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comics) { comic ->
                ComicItem(comic = comic)
            }
        }

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
fun ComicItem(comic: Comic) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 12.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = comic.title,
            color = MaterialTheme.colorScheme.background
        )
    }
}

data class Comic(
    val title: String,
    val imageUrl: String
)

@Preview(showBackground = true)
@Composable
fun PreviewMarvelGridScreen() {
    MarvelGridScreen( )
}

