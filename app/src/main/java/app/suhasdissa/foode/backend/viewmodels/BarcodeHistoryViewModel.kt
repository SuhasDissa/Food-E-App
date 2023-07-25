package app.suhasdissa.foode.backend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.foode.FoodeApplication
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepository
import kotlinx.coroutines.launch

class BarcodeHistoryViewModel(private val barcodeHistoryRepository: BarcodeHistoryRepository) :
    ViewModel() {
    var history: List<BarcodeEntity> by mutableStateOf(listOf())
        private set

    var favHistory: List<BarcodeEntity> by mutableStateOf(listOf())
        private set

    fun getHistory() {
        viewModelScope.launch {
            history = barcodeHistoryRepository.getAll()
            favHistory = history.filter { it.isFavourite }
        }
    }

    fun deleteItem(barcode: BarcodeEntity) {
        viewModelScope.launch {
            barcodeHistoryRepository.delete(barcode)
            getHistory()
        }
    }

    fun toggleFavourite(barcode: BarcodeEntity) {
        viewModelScope.launch {
            barcodeHistoryRepository.saveBarcode(barcode.toggleLike())
            getHistory()
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
