package es.smarting.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.smarting.rickmortyapp.ui.core.navigation.bottom.BottomBarItem
import es.smarting.rickmortyapp.ui.core.navigation.bottom.NavigationBottomWrapper

@Composable
fun HomeScreen(
    mainNavController: NavHostController
) {

    val navItemList = listOf(
        BottomBarItem.Episodes(),
        BottomBarItem.Characters()
    )

    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNavigation(navItemList, navController) }
    ) { padding ->

        Box (modifier = Modifier.padding(padding)) {
            NavigationBottomWrapper(navController, mainNavController)
        }

    }
}

@Composable
fun BottomNavigation(navItemList: List<BottomBarItem>, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        navItemList.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                label = { Text(item.title) },
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                //alwaysShowLabel = false
            )
        }
    }
}

