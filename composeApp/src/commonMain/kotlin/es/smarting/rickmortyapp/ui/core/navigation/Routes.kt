package es.smarting.rickmortyapp.ui.core.navigation

sealed class Routes(val route:String) {
    data object Home:Routes("home")
    data object Characters:Routes("characters")
    data object Episodes:Routes("episodes")
}