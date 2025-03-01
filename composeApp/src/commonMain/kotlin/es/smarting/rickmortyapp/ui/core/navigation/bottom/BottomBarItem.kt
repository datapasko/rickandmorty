package es.smarting.rickmortyapp.ui.core.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import es.smarting.rickmortyapp.ui.core.navigation.Routes

sealed class BottomBarItem {
    abstract val route: String
    abstract val title:String
    abstract val icon: ImageVector

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: ImageVector = Icons.Default.Place
    ):BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: ImageVector = Icons.Default.Person
    ):BottomBarItem()
}