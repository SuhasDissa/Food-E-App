package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.MainAdditivesModel
import app.suhasdissa.foode.ui.components.CardGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    additiveListViewModel: MainAdditivesModel = viewModel(factory = MainAdditivesModel.Factory),
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
            if (additiveListViewModel.additives.isNotEmpty()) {
                CardGrid(
                    additiveListViewModel.additives, modifier, onClickTextCard
                )
            }
        }
    }
}