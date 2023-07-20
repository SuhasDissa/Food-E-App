package app.suhasdissa.foode.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.BarcodeHistoryViewModel
import app.suhasdissa.foode.ui.components.BarcodeHistoryCard
import app.suhasdissa.foode.ui.components.IllustratedMessageScreen

@Composable
fun ScanHistoryScreen(
    onClickCard: (barcode: String) -> Unit,
    barcodeHistoryViewModel: BarcodeHistoryViewModel = viewModel(
        factory = BarcodeHistoryViewModel.Factory
    )
) {
    if (barcodeHistoryViewModel.history.isNotEmpty()) {
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
        ) {
            items(items = barcodeHistoryViewModel.history) {
                BarcodeHistoryCard(
                    onClick = { onClickCard(it.barcode) },
                    onClickRemove = { barcodeHistoryViewModel.deleteItem(it) },
                    barcodeEntity = it
                )
            }
        }
    } else {
        IllustratedMessageScreen(
            image = R.drawable.empty_barcode_list_icon,
            contentDescription = R.string.list_is_empty
        )
    }
}
