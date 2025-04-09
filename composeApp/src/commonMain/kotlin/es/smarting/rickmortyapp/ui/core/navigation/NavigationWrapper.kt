package es.smarting.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.smarting.rickmortyapp.ui.detail.CharacterDetailScreen
import es.smarting.rickmortyapp.ui.home.HomeScreen

@Composable
fun NavigationWrapper () {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route){
            HomeScreen(
                mainNavController
            )
        }

        composable<CharacterDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<CharacterDetail>()
            CharacterDetailScreen(
                id = route.id
            )
        }

    }
}