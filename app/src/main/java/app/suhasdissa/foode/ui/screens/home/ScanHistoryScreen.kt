package app.suhasdissa.foode.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import app.suhasdissa.foode.backend.viewmodels.BarcodeHistoryViewModel
import app.suhasdissa.foode.ui.components.BarcodeHistoryCard
import app.suhasdissa.foode.ui.components.BarcodeOptionSheet
import app.suhasdissa.foode.ui.components.IllustratedMessageScreen

@Composable
fun ScanHistoryScreen(
    onClickCard: (barcode: String) -> Unit,
    showFavourite: Boolean,
    barcodeHistoryViewModel: BarcodeHistoryViewModel = viewModel(
        factory = BarcodeHistoryViewModel.Factory
    )
) {
    val history by
        if (showFavourite) barcodeHistoryViewModel.favHistory.collectAsState() else barcodeHistoryViewModel.history.collectAsState()
    var selectedItem by remember { mutableStateOf<BarcodeEntity?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    if (history.isNotEmpty()) {
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
        ) {
            items(items = history) {
                BarcodeHistoryCard(
                    onClick = { onClickCard(it.barcode) },
                    onLongPress = {
                        selectedItem = it
                        showSheet = true
                    },
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

    if (showSheet) {
        selectedItem?.let {
            BarcodeOptionSheet(onDismissRequest = { showSheet = false }, barcode = it)
        }
    }
}
