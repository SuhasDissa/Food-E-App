package app.suhasdissa.foode

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.suhasdissa.foode.ui.screens.AboutScreen
import app.suhasdissa.foode.ui.screens.AdditiveDetailScreen
import app.suhasdissa.foode.ui.screens.HomeScreen
import app.suhasdissa.foode.ui.screens.SearchScreen
import app.suhasdissa.foode.ui.screens.SettingsScreen
import app.suhasdissa.foode.ui.screens.TwoPaneScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier, isLargeScreen: Boolean
) {
    AnimatedNavHost(
        navController = navController, startDestination = if (isLargeScreen) {
            Home.twoPaneRoute
        } else {
            Home.route
        }, modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(onClickTextCard = { id ->
                navController.navigateTo("${AdditiveDetail.route}/$id")
            }, onClickSearch = {
                navController.navigateTo(SearchView.route)
            }, onClickSettings = {
                navController.navigateTo(Settings.route)
            })
        }
        composable(route = Home.twoPaneRoute) {
            var additiveID by remember { mutableStateOf(0) }
            TwoPaneScreen(PaneOne = {
                HomeScreen(onClickTextCard = {
                    additiveID = it
                }, onClickSearch = {
                    navController.navigateTo(SearchView.twoPaneRoute)
                }, onClickSettings = {
                    navController.navigateTo(Settings.twoPaneRoute)
                })
            }, PaneTwo = {
                AdditiveDetailScreen(additiveID)
            })
        }
        composable(route = Settings.route) {
            SettingsScreen(onAboutClick = {
                navController.navigateTo(About.route)
            })
        }
        composable(route = About.route) {
            AboutScreen()
        }
        composable(route = SearchView.route) {
            SearchScreen(onClickTextCard = { id ->
                navController.navigateTo("${AdditiveDetail.route}/$id")
            })
        }
        composable(route = SearchView.twoPaneRoute) {
            var additiveID by remember { mutableStateOf(0) }
            TwoPaneScreen(PaneOne = {
                SearchScreen(onClickTextCard = { additiveID = it })
            }, PaneTwo = { AdditiveDetailScreen(additiveID) })

        }
        composable(route = Settings.twoPaneRoute) {
            TwoPaneScreen(PaneOne = {
                SettingsScreen(onAboutClick = { })
            }, PaneTwo = { AboutScreen() })
        }
        composable(
            route = AdditiveDetail.routeWithArgs, arguments = AdditiveDetail.arguments
        ) {
            val id = it.arguments?.getInt("SongID")
            if (id != null) {
                AdditiveDetailScreen(id)
            }
        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}