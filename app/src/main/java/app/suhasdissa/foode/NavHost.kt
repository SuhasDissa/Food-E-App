package app.suhasdissa.foode

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import app.suhasdissa.foode.ui.screens.home.HomeScreen
import app.suhasdissa.foode.ui.screens.home.search.SearchScreen
import app.suhasdissa.foode.ui.screens.settings.SettingsScreen
import app.suhasdissa.foode.ui.screens.settings.about.AboutScreen
import app.suhasdissa.foode.ui.screens.additivedetails.AdditiveView
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController, startDestination = Home.route, modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(onClickTextCard = { id ->
                navController.navigateTo("${SongViewer.route}/$id")
            }, onClickSearch = {
                navController.navigateTo(SearchView.route)
            }, onClickSettings = {
                navController.navigateTo(Settings.route)
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
                navController.navigateTo("${SongViewer.route}/$id")
            })
        }
        composable(
            route = SongViewer.routeWithArgs, arguments = SongViewer.arguments
        ) {
            val id = it.arguments?.getInt("SongID")
            if (id != null) {
                AdditiveView(id)
            }
        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}