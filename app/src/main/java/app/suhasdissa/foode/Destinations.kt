package app.suhasdissa.foode

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object Home : Destination {
    override val route = "home"
    val twoPaneRoute = "home2pane"
}

object SearchView : Destination {
    override val route = "searchview"
    val twoPaneRoute = "search2pane"
}

object Settings : Destination {
    override val route = "settings"
    val twoPaneRoute = "settings2pane"
}

object About : Destination {
    override val route = "about"
}

object AdditiveDetail : Destination {
    override val route = "detailviewer"
    val routeWithArgs = "$route/{SongID}"
    val arguments = listOf(navArgument("SongID") { type = NavType.IntType })
}