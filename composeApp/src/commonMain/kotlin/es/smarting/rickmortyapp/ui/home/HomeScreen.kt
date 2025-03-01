package es.smarting.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.smarting.rickmortyapp.ui.core.navigation.bottom.BottomBarItem
import es.smarting.rickmortyapp.ui.core.navigation.bottom.NavigationBottomWrapper

@Composable
fun HomeScreen() {

    val navItemList = listOf(
        BottomBarItem.Characters(),
        BottomBarItem.Episodes()
    )

    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomNavigation(navItemList, navController) }
    ) {

        Box {
            NavigationBottomWrapper(navController)
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
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
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

