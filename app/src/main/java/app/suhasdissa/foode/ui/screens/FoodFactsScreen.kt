package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FoodFactScreen(barcode: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(barcode)
    }
}