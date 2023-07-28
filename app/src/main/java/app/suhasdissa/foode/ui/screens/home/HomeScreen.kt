package app.suhasdissa.foode.ui.screens.home

import android.view.SoundEffectConstants
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.suhasdissa.foode.Destination
import app.suhasdissa.foode.R
import app.suhasdissa.foode.navigateTo
import app.suhasdissa.foode.ui.components.NavDrawerContent
import app.suhasdissa.foode.ui.screens.additives.MainAdditivesScreen
import app.suhasdissa.foode.ui.screens.food_fact_screen.MainBarcodeHistoryScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onClickTextCard: (url: Int) -> Unit,
    onNavigate: (Destination) -> Unit,
    onClickBarcodeCard: (barcode: String) -> Unit

) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    var currentDestination by remember {
        mutableStateOf<Destination>(Destination.Additives)
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            NavDrawerContent(currentDestination = currentDestination, onDestinationSelected = {
                scope.launch {
                    drawerState.close()
                }
                if (it == Destination.Settings) {
                    onNavigate(it)
                } else {
                    navController.navigateTo(it.route)
                    currentDestination = it
                }
            })
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            CenterAlignedTopAppBar(navigationIcon = {
                IconButton(onClick = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Open Navigation Drawer"
                    )
                }
            }, title = {
                Text(
                    stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.primary
                )
            })
        }) { paddingValues ->
            NavHost(
                navController,
                startDestination = Destination.Additives.route,
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable(Destination.Additives.route) {
                    MainAdditivesScreen(onNavigate, onClickTextCard)
                }
                composable(Destination.FoodFacts.route) {
                    MainBarcodeHistoryScreen(onNavigate, onClickBarcodeCard)
                }
            }
        }
    }
}
