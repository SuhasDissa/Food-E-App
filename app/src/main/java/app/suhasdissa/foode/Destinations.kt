package app.suhasdissa.foode

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object Home : Destination {
    override val route = "home"
}

object SearchView : Destination {
    override val route = "searchview"
}

object Settings : Destination {
    override val route = "settings"
}

object About : Destination {
    override val route = "about"
}

object SongViewer : Destination {
    override val route = "songviewer"
    val routeWithArgs = "$route/{SongID}"
    val arguments = listOf(navArgument("SongID") { type = NavType.IntType })
}