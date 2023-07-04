package app.suhasdissa.foode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import app.suhasdissa.foode.ui.components.TwoPaneScreen
import app.suhasdissa.foode.ui.screens.AboutScreen
import app.suhasdissa.foode.ui.screens.AdditiveDetailScreen
import app.suhasdissa.foode.ui.screens.SearchScreen
import app.suhasdissa.foode.ui.screens.SettingsScreen
import app.suhasdissa.foode.ui.screens.food_fact_screen.FoodFactScreen
import app.suhasdissa.foode.ui.screens.food_fact_search.FoodFactSearchScreen
import app.suhasdissa.foode.ui.screens.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier, isLargeScreen: Boolean
) {
    NavHost(
        navController = navController, startDestination = Home.route, modifier = modifier
    ) {
        composable(route = Home.route) {
            var additiveID by remember { mutableStateOf(0) }
            if (isLargeScreen) {
                TwoPaneScreen(PaneOne = {
                    HomeScreen(onClickTextCard = {
                        additiveID = it
                    }, onNavigate = { destination ->
                        navController.navigateTo(destination.route)
                    }, onClickBarcodeCard = { barcode ->
                        navController.navigateTo("${FoodFactDetail.route}/$barcode")
                    })
                }, PaneTwo = {
                    AdditiveDetailScreen(additiveID)
                })
            } else {
                HomeScreen(onClickTextCard = { id ->
                    navController.navigateTo("${AdditiveDetail.route}/$id")
                }, onNavigate = {
                    navController.navigateTo(it.route)
                }, onClickBarcodeCard = { barcode ->
                    navController.navigateTo("${FoodFactDetail.route}/$barcode")
                })
            }
        }
        composable(route = FoodFactSearch.route) {
            FoodFactSearchScreen(onClickCard = { barcode ->
                navController.navigateTo("${FoodFactDetail.route}/$barcode")
            })
        }
        composable(route = Settings.route) {
            if (isLargeScreen) {
                TwoPaneScreen(PaneOne = {
                    SettingsScreen(onAboutClick = { })
                }, PaneTwo = { AboutScreen() })
            } else {
                SettingsScreen(onAboutClick = {
                    navController.navigateTo(About.route)
                })
            }
        }
        composable(route = About.route) {
            AboutScreen()
        }
        composable(route = SearchView.route) {
            var additiveID by remember { mutableStateOf(0) }
            if (isLargeScreen) {
                TwoPaneScreen(PaneOne = {
                    SearchScreen(onClickTextCard = { additiveID = it })
                }, PaneTwo = { AdditiveDetailScreen(additiveID) })
            } else {
                SearchScreen(onClickTextCard = { id ->
                    navController.navigateTo("${AdditiveDetail.route}/$id")
                })
            }
        }
        composable(
            route = AdditiveDetail.routeWithArgs, arguments = AdditiveDetail.arguments
        ) {
            val id = it.arguments?.getInt("AdditiveID")
            if (id != null) {
                AdditiveDetailScreen(id)
            }
        }
        composable(
            route = FoodFactDetail.routeWithArgs,
            arguments = FoodFactDetail.arguments,
            deepLinks = listOf(navDeepLink {
                uriPattern = "foode://${FoodFactDetail.routeWithArgs}"
            })
        ) {
            val barcode = it.arguments?.getString("barcode")
            if (barcode != null) {
                FoodFactScreen(barcode, onCLickAdditiveCard = { id ->
                    navController.navigateTo("${AdditiveDetail.route}/$id")
                })
            }
        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}