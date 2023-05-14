package app.suhasdissa.foode.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.suhasdissa.foode.AppNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FoodeApp(windowSizeClass: WindowSizeClass) {
    val navController = rememberAnimatedNavController()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavHost(
            navController = navController,
            modifier = Modifier,
            isLargeScreen = (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact)
        )

    }
}