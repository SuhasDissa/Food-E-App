package app.suhasdissa.foode.backend.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.foode.FoodeApplication
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.BarcodeHistoryRepository
import app.suhasdissa.foode.backend.repositories.OpenFoodFactRepository
import kotlinx.coroutines.launch

sealed interface FoodFactUiState {
    data class Success(val product: Product) : FoodFactUiState
    data class Error(val error: String) : FoodFactUiState
    object ProductNotFound : FoodFactUiState
    object Loading : FoodFactUiState
}

class FoodFactsViewModel(
    private val openFoodFactRepository: OpenFoodFactRepository,
    private val barcodeHistoryRepository: BarcodeHistoryRepository,
    private val additivesRepository: AdditivesRepository
) : ViewModel() {

    var foodFactUiState: FoodFactUiState by mutableStateOf(FoodFactUiState.Loading)

    private var currentBarcode: String? = null
    var eachAdditive = mutableStateListOf<Pair<String, AdditivesEntity?>>()
        private set

    var ingredientsWithBoldAllergens by mutableStateOf<AnnotatedString?>(null)
        private set

    fun getProduct(barcode: String) {
        if (barcode == currentBarcode) return

        viewModelScope.launch {
            foodFactUiState = FoodFactUiState.Loading
            foodFactUiState = try {
                val product = openFoodFactRepository.getOnlineData(barcode)
                product?.let {
                    it.code?.let { code ->
                        addBarcode(
                            BarcodeEntity(
                                code, it.productName ?: "Unknown Product", it.imageUrl ?: ""
                            )
                        )
                    }
                    currentBarcode = barcode
                    searchEach(it.additivesTags)
                    it.ingredientsTextWithAllergens?.let { text ->
                        formatAllergens(text)
                    }
                    FoodFactUiState.Success(it)
                } ?: FoodFactUiState.ProductNotFound
            } catch (_: retrofit2.HttpException) {
                FoodFactUiState.ProductNotFound
            } catch (e: Exception) {
                FoodFactUiState.Error(e.toString())
            }
        }
    }

    private fun searchEach(additives: List<String>) {
        viewModelScope.launch {
            val additiveRegex = Regex("[eE][\\d]+")
            var result = additiveRegex.find(additives.joinToString(" "))
            eachAdditive.clear()
            while (result != null) {
                val selectedAdditive = additivesRepository.search("%${result.value}%").firstOrNull()
                eachAdditive.add(result.value to selectedAdditive)
                result = result.next()
            }
        }
    }

    private fun formatAllergens(text: String) {

        viewModelScope.launch {
            val boldRegex = Regex("<[^</>]+>([^</>]+)</[^</>]+>")
            var results: MatchResult? = boldRegex.find(text)
            val keywords = mutableListOf<String>()
            val finalText = text.replace(Regex("<[^<>]+>"), "")
            while (results != null) {
                keywords.add(results.groupValues[1])
                results = results.next()
            }
            ingredientsWithBoldAllergens = buildAnnotatedString {
                append(finalText)
                keywords.forEach { keyword ->
                    val indexOf = finalText.indexOf(keyword)
                    addStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                        start = indexOf,
                        end = indexOf + keyword.length
                    )
                }
            }
        }
    }

    private fun addBarcode(barcode: BarcodeEntity) {
        viewModelScope.launch {
            barcodeHistoryRepository.saveBarcode(barcode)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                FoodFactsViewModel(
                    application.container.openFoodFactRepository,
                    application.container.barcodeHistoryRepository,
                    application.container.additivesRepository
                )
            }
        }
    }
}