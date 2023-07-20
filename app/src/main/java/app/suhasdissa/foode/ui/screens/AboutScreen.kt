package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.CheckUpdateViewModel
import app.suhasdissa.foode.ui.components.SettingItem
import app.suhasdissa.foode.utils.openBrowser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    updateViewModel: CheckUpdateViewModel = viewModel()
) {
    val context = LocalContext.current

    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.about_title)) })
    }) { innerPadding ->
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                SettingItem(
                    title = stringResource(R.string.readme),
                    description = stringResource(R.string.check_repo_and_readme),
                    onClick = { openBrowser(context, "https://github.com/SuhasDissa/Food-E-App/") },
                    icon = Icons.Default.Description
                )
            }
            item {
                SettingItem(
                    title = stringResource(R.string.latest_release),
                    description = "${updateViewModel.latestVersion}",
                    onClick = {
                        openBrowser(
                            context,
                            "https://github.com/SuhasDissa/Food-E-App/releases/latest"
                        )
                    },
                    icon = Icons.Default.NewReleases
                )
            }
            item {
                SettingItem(
                    title = stringResource(R.string.github_issue),
                    description = stringResource(R.string.github_issue_description),
                    onClick = {
                        openBrowser(
                            context,
                            "https://github.com/SuhasDissa/Food-E-App/issues"
                        )
                    },
                    icon = Icons.Default.ContactSupport
                )
            }
            item {
                SettingItem(
                    title = stringResource(R.string.current_version),
                    description = "${updateViewModel.currentVersion}",
                    onClick = {
                        openBrowser(
                            context,
                            "https://github.com/SuhasDissa/Food-E-App/issues"
                        )
                    },
                    icon = Icons.Default.Info
                )
            }
        }
    }
}

@Composable
@Preview
fun AboutScreenPreview() {
    AboutScreen()
}
