package app.suhasdissa.foode.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.foode.FoodeApplication
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BarcodeHistoryViewModel(private val barcodeHistoryRepository: BarcodeHistoryRepository) :
    ViewModel() {
    val history: StateFlow<List<BarcodeEntity>> = barcodeHistoryRepository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf()
    )

    val favHistory: StateFlow<List<BarcodeEntity>> = barcodeHistoryRepository.getFav().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf()
    )

    fun deleteItem(barcode: BarcodeEntity) {
        viewModelScope.launch {
            barcodeHistoryRepository.delete(barcode)
        }
    }

    fun toggleFavourite(barcode: BarcodeEntity) {
        viewModelScope.launch {
            barcodeHistoryRepository.saveBarcode(barcode.toggleLike())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                BarcodeHistoryViewModel(application.container.barcodeHistoryRepository)
            }
        }
    }
}
