package app.suhasdissa.foode.ui.screens.home

import android.Manifest
import android.R.attr.value
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
    onClickSearch: () -> Unit
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
        HomeScreen.Additives,
        HomeScreen.Favourites,
        HomeScreen.Scan
    )

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = { onClickSettings() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_title)
                )
            }
        }, modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer))
    }, floatingActionButton = {
        val context = LocalContext.current
        when (currentScreen) {
            HomeScreen.Scan -> {
                FloatingActionButton(onClick = {
                    val permission = Manifest.permission.CAMERA
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, permission)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        context.startActivity(
                            Intent(
                                context,
                                BarcodeScannerActivity::class.java
                            )
                        )
                    } else {
                        cameraPermission = false
                        launcher.launch(permission)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.QrCodeScanner,
                        stringResource(R.string.scan)
                    )
                }
            }

            else -> {
                FloatingActionButton(onClick = { onClickSearch() }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        stringResource(R.string.search_icon_hint)
                    )
                }
            }
        }
    },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
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
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = HomeScreen.Additives.route,
            Modifier.padding(innerPadding)
        ) {
            composable(HomeScreen.Additives.route) {
                AdditiveScreen(onClickTextCard = onClickTextCard)
            }
            composable(HomeScreen.Favourites.route) {
                FavouritesScreen(onClickTextCard = onClickTextCard)
            }
            composable(HomeScreen.Scan.route) {
                ScanHistoryScreen()
            }
        }
    }
}

sealed class HomeScreen(val icon: ImageVector, val route: String, @StringRes val resourceId: Int) {
    object Additives :
        HomeScreen(Icons.Filled.Book, "all_additives", R.string.additives)

    object Favourites :
        HomeScreen(Icons.Filled.Star, "fav_additives", R.string.favourites)

    object Scan : HomeScreen(Icons.Filled.QrCodeScanner, "scan_history", R.string.scan)
}
