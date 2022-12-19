package app.suhasdissa.foode.ui.screens.home.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.backend.viewmodels.MainAdditivesModel
import app.suhasdissa.foode.ui.components.CardGrid

@Composable
fun MainAdditivesScreen(
    modifier: Modifier = Modifier,
    songViewModel: MainAdditivesModel = viewModel(factory = MainAdditivesModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    if (songViewModel.additives.isNotEmpty()) {
        CardGrid(
            songViewModel.additives, modifier, onClickTextCard
        )
    }

}