package com.heroes.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.heroes.marvelapp.domain.model.getCharacters.CharactersMarvel
import com.heroes.marvelapp.presentation.ui.ComicDetailScreen
import com.heroes.marvelapp.presentation.ui.MarvelGridScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail")
}

@Composable
fun AppNavGraph(modifier: Modifier, navController: NavHostController) {
    var dataSelected  = CharactersMarvel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MarvelGridScreen(
                modifier = modifier,
                onSelectItem = {
                    dataSelected = it
                    navController.navigate(Screen.Detail.route)
                }
            )
        }
        composable(Screen.Detail.route) {
            ComicDetailScreen(
                dataSelected = dataSelected
            ) {
                navController.popBackStack()
            }
        }
    }
}
