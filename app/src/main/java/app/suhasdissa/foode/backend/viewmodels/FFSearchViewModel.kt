package app.suhasdissa.foode.backend.viewmodels

import android.util.Log
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
import app.suhasdissa.foode.backend.models.Products
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepository
import app.suhasdissa.foode.backend.repositories.OpenFoodFactRepository
import app.suhasdissa.foode.backend.viewmodels.states.FFSearchError
import app.suhasdissa.foode.backend.viewmodels.states.FFSearchState
import app.suhasdissa.foode.utils.toBarcodeEntity
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class FFSearchViewModel(
    private val openFoodFactRepository: OpenFoodFactRepository,
    private val barcodeHistoryRepository: BarcodeHistoryRepository
) : ViewModel() {

    var state: FFSearchState by mutableStateOf(FFSearchState.Empty)
        private set

    fun searchFoodProduct(query: String) {
        viewModelScope.launch {
            state = FFSearchState.Loading
            state = try {
                val products = openFoodFactRepository.searchProduct(query)
                if (products.isNotEmpty()) {
                    FFSearchState.Success(
                        products
                    )
                } else {
                    FFSearchState.NotFound
                }
            } catch (_: SocketTimeoutException) {
                FFSearchState.Error(FFSearchError.Timeout)
            } catch (e: Exception) {
                Log.e("SearchError", e.toString())
                FFSearchState.Error(FFSearchError.Unknown)
            }
        }
    }

    fun addToHistory(item: Products) {
        viewModelScope.launch {
            barcodeHistoryRepository.saveBarcode(item.toBarcodeEntity())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                FFSearchViewModel(
                    application.container.openFoodFactRepository,
                    application.container.barcodeHistoryRepository
                )
            }
        }
    }
}