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

object AdditiveDetail : Destination {
    override val route = "detailviewer"
    val routeWithArgs = "$route/{AdditiveID}"
    val arguments = listOf(navArgument("AdditiveID") { type = NavType.IntType })
}


object FoodFactDetail : Destination {
    override val route = "food_fact"
    val routeWithArgs = "$route/{barcode}"
    val arguments = listOf(navArgument("barcode") { type = NavType.StringType })
}

object FoodFactSearch : Destination {
    override val route = "food_fact_search"
}