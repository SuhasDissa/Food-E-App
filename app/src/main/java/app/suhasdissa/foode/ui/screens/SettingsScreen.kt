package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.suhasdissa.foode.Destination
import app.suhasdissa.foode.R
import app.suhasdissa.foode.ui.components.SettingItem
import app.suhasdissa.foode.utils.autoTranslateKey
import app.suhasdissa.foode.utils.rememberPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Destination) -> Unit
) {
    var autoTranslate by rememberPreference(key = autoTranslateKey, defaultValue = false)

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.settings_title)) }
        )
    }) { innerPadding ->
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                SettingItem(
                    title = "Cloud Translation",
                    description = "Auto translate additive details with lingva",
                    onClick = { autoTranslate = !autoTranslate },
                    icon = Icons.Default.Translate,
                    TrailingContent = {
                        Switch(checked = autoTranslate, onCheckedChange = { autoTranslate = it })
                    }
                )
            }
            item {
                SettingItem(
                    title = stringResource(R.string.about_title),
                    description = stringResource(R.string.about_setting_description),
                    onClick = { onNavigate(Destination.About) },
                    icon = Icons.Outlined.Info
                )
            }
        }
    }
}
