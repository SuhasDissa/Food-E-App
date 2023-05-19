package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.MainAdditivesModel
import app.suhasdissa.foode.ui.components.CardGrid
import app.suhasdissa.foode.ui.components.MessageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    additiveListViewModel: MainAdditivesModel = viewModel(factory = MainAdditivesModel.Factory),
    onClickTextCard: (url: Int) -> Unit,
    onClickSettings: () -> Unit,
    onClickSearch: () -> Unit
) {
    var isFavourite by remember { mutableStateOf(additiveListViewModel.listFavourite) }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) }, actions = {
            IconButton(onClick = {
                isFavourite = !isFavourite
                additiveListViewModel.listFavourite = isFavourite
                additiveListViewModel.getAdditives()
            }) {
                when (isFavourite) {
                    false -> Icon(
                        imageVector = Icons.Filled.StarOutline,
                        contentDescription = stringResource(R.string.add_to_favourite)
                    )

                    true -> Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(R.string.remove_from_favourite)
                    )
                }
            }
            IconButton(onClick = { onClickSettings() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.settings_title)
                )
            }
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { onClickSearch() }) {
            Icon(imageVector = Icons.Filled.Search, stringResource(R.string.search_icon_hint))
        }
    }) { innerPadding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (additiveListViewModel.additives.isNotEmpty()) {
                CardGrid(
                    additiveListViewModel.additives, modifier, onClickTextCard
                )
            } else {
                MessageScreen(message = R.string.list_is_empty)
            }
        }
    }
}