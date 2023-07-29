package app.suhasdissa.foode.ui.screens.additives

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.MainAdditivesViewModel
import app.suhasdissa.foode.ui.components.CardGrid
import app.suhasdissa.foode.ui.components.MessageScreen

@Composable
fun AdditiveScreen(
    additiveListViewModel: MainAdditivesViewModel = viewModel(
        factory = MainAdditivesViewModel.Factory
    ),
    onClickTextCard: (url: Int) -> Unit
) {
    val additives by additiveListViewModel.additives.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
    ) {
        if (additives.isNotEmpty()) {
            CardGrid(
                additives,
                Modifier,
                onClickTextCard
            )
        } else {
            MessageScreen(message = R.string.list_is_empty)
        }
    }
}
