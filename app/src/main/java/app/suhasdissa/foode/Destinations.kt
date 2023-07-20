package app.suhasdissa.foode

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object SearchView : Destination("searchview")
    object Settings : Destination("settings")
    object About : Destination("about")
    object AdditiveDetail : Destination("detailviewer") {
        val routeWithArgs = "$route/{AdditiveID}"
        val arguments = listOf(navArgument("AdditiveID") { type = NavType.IntType })
    }
    object FoodFactDetail : Destination("food_fact") {
        val routeWithArgs = "$route/{barcode}"
        val arguments = listOf(navArgument("barcode") { type = NavType.StringType })
    }
    object FoodFactSearch : Destination("food_fact_search")
}
