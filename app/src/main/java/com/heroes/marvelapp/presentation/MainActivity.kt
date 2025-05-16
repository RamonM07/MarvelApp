package com.heroes.marvelapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.heroes.marvelapp.presentation.navigation.AppNavGraph
import com.heroes.marvelapp.presentation.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), containerColor =
                        MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    val navController = rememberNavController()
                    AppNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Marvel App",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyHorizontalGrid(
            modifier = Modifier.fillMaxWidth(),
            rows = GridCells.Fixed(4)

        ) {

        }
        repeat(10){
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(vertical = 12.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Color"
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelAppTheme {
        Greeting()
    }
}