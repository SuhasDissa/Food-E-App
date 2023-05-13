package app.suhasdissa.foode.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.suhasdissa.foode.R
import app.suhasdissa.foode.ui.screens.additivedetails.AdditiveDetailView
import app.suhasdissa.foode.ui.screens.home.tabs.MainAdditivesScreen

@Composable
fun HomeScreen(
    onClickTextCard: (url: Int) -> Unit,
    onClickSettings: () -> Unit,
    onClickSearch: () -> Unit
) {
    val isBigScreen = true
    var currentAdditiveID by remember { mutableStateOf(0) }
    if (isBigScreen) {
        Row(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
            ) {
                AdditivesList(
                    onClickTextCard = { currentAdditiveID = it },
                    onClickSettings = onClickSettings,
                    onClickSearch = onClickSearch
                )
            }
            Column(
                Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
            ) {
                AdditiveDetailView(id = currentAdditiveID)
            }

        }
    } else {
        AdditivesList(
            onClickTextCard = onClickTextCard,
            onClickSettings = onClickSettings,
            onClickSearch = onClickSearch
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditivesList(
    modifier: Modifier = Modifier,
    onClickTextCard: (url: Int) -> Unit,
    onClickSettings: () -> Unit,
    onClickSearch: () -> Unit
) {
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = { onClickSettings() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Search"
                )
            }

        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { onClickSearch() }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        }
    }) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainAdditivesScreen(modifier, onClickTextCard = onClickTextCard)
        }
    }
}