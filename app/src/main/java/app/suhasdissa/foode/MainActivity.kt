package app.suhasdissa.foode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.suhasdissa.foode.ui.FoodeApp
import app.suhasdissa.foode.ui.theme.FoodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodeTheme {
                FoodeApp()
            }
        }
    }
}