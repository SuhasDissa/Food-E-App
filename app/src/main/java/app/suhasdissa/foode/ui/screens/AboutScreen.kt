package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.CheckUpdateViewModel
import app.suhasdissa.foode.utils.openBrowser
import coil.compose.AsyncImage
import coil.request.ImageRequest

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
        Column(
            modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(
                stringResource(R.string.developer_heading),
                style = MaterialTheme.typography.headlineSmall
            )
            Card(modifier.fillMaxWidth()) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = modifier
                            .size(128.dp)
                            .clip(CircleShape),
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data("https://avatars.githubusercontent.com/SuhasDissa?size=128")
                            .crossfade(true).build(),
                        contentDescription = stringResource(R.string.avatar_description)
                    )
                    Column(
                        modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.developer_name)
                        )
                        Text(
                            text = stringResource(R.string.developer_username),
                        )
                        Row(
                            modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(onClick = {
                                openBrowser(
                                    context, "https://github.com/SuhasDissa"
                                )
                            }) {

                                Icon(
                                    painterResource(R.drawable.ic_github),
                                    contentDescription = stringResource(R.string.github_icon_hint),
                                    modifier.size(48.dp)
                                )
                            }
                            IconButton(onClick = {
                                openBrowser(
                                    context, "https://twitter.com/SuhasDissa"
                                )
                            }) {

                                Icon(
                                    painterResource(R.drawable.ic_twitter),
                                    contentDescription = stringResource(R.string.twitter_icon_hint),
                                    modifier.size(48.dp)
                                )
                            }
                        }
                    }
                }
            }
            Card {
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(stringResource(R.string.current_version) +" ${updateViewModel.currentVersion}")
                    Text(stringResource(R.string.latest_version) +" ${updateViewModel.latestVersion}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.update_description)
                        )
                        Button(
                            onClick = {
                                openBrowser(
                                    context,
                                    "https://github.com/SuhasDissa/Food-E-App/releases/latest"
                                )
                            },
                            enabled = updateViewModel.isUpdateAvailable
                        ) {
                            Text(
                                stringResource(R.string.update_btn)
                            )
                        }
                    }
                }
            }
        }
    }
}