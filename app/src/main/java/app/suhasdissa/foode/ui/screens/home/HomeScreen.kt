package app.suhasdissa.foode.ui.screens.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.suhasdissa.foode.BarcodeScannerActivity
import app.suhasdissa.foode.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickTextCard: (url: Int) -> Unit,
    onClickSettings: () -> Unit,
    onClickSearch: () -> Unit,
    onClickBarcodeCard: (barcode: String) -> Unit

) {
    var cameraPermission by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        cameraPermission = it
    }
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf<HomeScreen>(HomeScreen.Additives) }
    val items = listOf(
        HomeScreen.Additives, HomeScreen.Favourites, HomeScreen.Scan
    )

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = { onClickSettings() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_title)
                )
            }
        })
    }, floatingActionButton = {
        val context = LocalContext.current

        Column {
            FloatingActionButton(onClick = {
                val permission = Manifest.permission.CAMERA
                val permissionCheckResult = ContextCompat.checkSelfPermission(context, permission)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(
                        Intent(
                            context, BarcodeScannerActivity::class.java
                        )
                    )
                } else {
                    cameraPermission = false
                    launcher.launch(permission)
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.barcode_icon),
                    stringResource(R.string.scan)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            FloatingActionButton(onClick = { onClickSearch() }) {
                Icon(
                    imageVector = Icons.Filled.Search, stringResource(R.string.search_icon_hint)
                )
            }
        }
    }, bottomBar = {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                NavigationBarItem(icon = screen.icon,
                    label = { Text(stringResource(screen.resourceId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        currentScreen = screen
                    })
            }
        }
    }) { innerPadding ->
        NavHost(
            navController,
            startDestination = HomeScreen.Additives.route,
            Modifier.padding(innerPadding)
        ) {
            composable(HomeScreen.Additives.route, enterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.Favourites.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )

                    HomeScreen.Scan.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )

                    else -> null
                }
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
                )
            }) {
                AdditiveScreen(onClickTextCard = onClickTextCard)
            }
            composable(HomeScreen.Favourites.route, enterTransition = {
                when (initialState.destination.route) {
                    HomeScreen.Additives.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )

                    HomeScreen.Scan.route -> slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )

                    else -> null
                }
            }, exitTransition = {
                when (targetState.destination.route) {
                    HomeScreen.Additives.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )

                    HomeScreen.Scan.route -> slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )

                    else -> null
                }
            }) {
                FavouritesScreen(onClickTextCard = onClickTextCard)
            }
            composable(HomeScreen.Scan.route, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)
                )
            }) {
                ScanHistoryScreen(onClickCard = onClickBarcodeCard)
            }
        }
    }
}

sealed class HomeScreen(
    val icon: @Composable () -> Unit, val route: String, @StringRes val resourceId: Int
) {
    object Additives : HomeScreen(
        { Icon(imageVector = Icons.Filled.Book, contentDescription = null) },
        "all_additives",
        R.string.additives
    )

    object Favourites : HomeScreen({
        Icon(
            imageVector = Icons.Filled.Star, contentDescription = null
        )
    }, "fav_additives", R.string.favourites)

    object Scan : HomeScreen({
        Icon(
            painter = painterResource(id = R.drawable.barcode_icon), contentDescription = null
        )
    }, "scan_history", R.string.scan_history)
}
