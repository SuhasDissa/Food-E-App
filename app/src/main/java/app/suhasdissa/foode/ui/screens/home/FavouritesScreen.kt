package app.suhasdissa.foode.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.MainAdditivesViewModel
import app.suhasdissa.foode.ui.components.CardGrid
import app.suhasdissa.foode.ui.components.IllustratedMessageScreen

@Composable
fun FavouritesScreen(
    additiveListViewModel: MainAdditivesViewModel = viewModel(factory = MainAdditivesViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        if (additiveListViewModel.favAdditives.isNotEmpty()) {
            CardGrid(
                additiveListViewModel.favAdditives, Modifier, onClickTextCard
            )
        } else {
            IllustratedMessageScreen(
                image = R.drawable.empty_favourites_list_icon,
                contentDescription = R.string.favourites_list_is_empty
            )
        }
    }
}