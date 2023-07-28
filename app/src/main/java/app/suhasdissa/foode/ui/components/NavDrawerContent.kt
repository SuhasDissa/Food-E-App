package app.suhasdissa.foode.ui.components

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.view.SoundEffectConstants
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import app.suhasdissa.foode.BarcodeScannerActivity
import app.suhasdissa.foode.Destination
import app.suhasdissa.foode.R

@Composable
fun NavDrawerContent(
    currentDestination: Destination,
    onDestinationSelected: (Destination) -> Unit
) {
    var cameraPermission by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        cameraPermission = it
    }
    val context = LocalContext.current
    val view = LocalView.current
    ModalDrawerSheet(modifier = Modifier.width(250.dp)) {
        Spacer(Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(96.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Text(
                stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.EnergySavingsLeaf,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(id = R.string.additives)) },
            selected = currentDestination == Destination.Additives,
            onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                onDestinationSelected(Destination.Additives)
            }
        )
        Spacer(Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.open_food_fact_icon),
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(id = R.string.food_products)) },
            selected = currentDestination == Destination.FoodFacts,
            onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                onDestinationSelected(Destination.FoodFacts)
            }
        )
        Spacer(Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.barcode_icon),
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(id = R.string.scan_food_product)) },
            selected = false,
            onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
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
            }
        )
        Spacer(Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            label = { Text(text = stringResource(id = R.string.settings_title)) },
            selected = false,
            onClick = {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                onDestinationSelected(Destination.Settings)
            }
        )
    }
}
