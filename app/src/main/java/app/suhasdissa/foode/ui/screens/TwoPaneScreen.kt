package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TwoPaneScreen(
    PaneOne: @Composable () -> Unit,
    PaneTwo: @Composable () -> Unit
) {
    Row(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .weight(0.5f)
                .fillMaxHeight()
        ) {
            PaneOne()
        }
        Column(
            Modifier
                .weight(0.5f)
                .fillMaxHeight()
        ) {
            PaneTwo()
        }

    }

}