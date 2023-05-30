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
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.repositories.OpenFoodFactRepository
import kotlinx.coroutines.launch

class BarcodeScannerViewModel(private val openFoodFactRepository: OpenFoodFactRepository) :
    ViewModel() {

    var product by mutableStateOf<Product?>(null)

    fun getProduct(barcode: String) {
        viewModelScope.launch {
            product = openFoodFactRepository.getOnlineData(barcode)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                val openFoodFactRepository = application.container.openFoodFactRepository
                BarcodeScannerViewModel(openFoodFactRepository = openFoodFactRepository)
            }
        }
    }
}