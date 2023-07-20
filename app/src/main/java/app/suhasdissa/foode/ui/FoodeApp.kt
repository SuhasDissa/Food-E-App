package app.suhasdissa.foode.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import app.suhasdissa.foode.AppNavHost

@Composable
fun FoodeApp(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
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
