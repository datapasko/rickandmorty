package es.smarting.rickmortyapp.ui.core.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.smarting.rickmortyapp.ui.core.navigation.CharactersNav
import es.smarting.rickmortyapp.ui.core.navigation.EpisodesNav
import es.smarting.rickmortyapp.ui.core.navigation.Routes
import es.smarting.rickmortyapp.ui.home.tabs.characters.CharactersScreen
import es.smarting.rickmortyapp.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper (
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Routes.Episodes.route
    ) {

        composable(Routes.Characters.route) {
            CharactersScreen()
        }

        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }
    }
}