package app.suhasdissa.foode.backend.viewmodels

import android.content.ClipboardManager
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
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.TranslationRepository
import app.suhasdissa.foode.backend.viewmodels.states.TranslationState
import java.util.Locale
import kotlinx.coroutines.launch

class AdditiveDetailViewModel(
    private val additivesRepository: AdditivesRepository,
    private val clipboard: ClipboardManager,
    private val transtationRepository: TranslationRepository
) : ViewModel() {
    var additive: AdditivesEntity? by mutableStateOf(null)
        private set
    var translationState: TranslationState by mutableStateOf(TranslationState.NotTranslated)
        private set

    private val languageCode: String = Locale.getDefault().language
    private var supportedLanguages = listOf<String>()

    var translatable by mutableStateOf((languageCode != "en"))
        private set

    init {
        getLanguages()
    }

    private fun getLanguages() {
        if (!translatable) return
        viewModelScope.launch {
            runCatching {
                supportedLanguages = transtationRepository.getLanguages().map { it.code }
            }
            translatable =
                supportedLanguages.isNotEmpty() && supportedLanguages.contains(languageCode)
        }
    }
    private fun getTranslation(query: String) {
        if (!translatable) return
        viewModelScope.launch {
            translationState = TranslationState.Loading
            translationState = try {
                val translation = transtationRepository.getTranslation(languageCode, query)
                TranslationState.Success(translation)
            } catch (e: Exception) {
                Log.e("Translate Error", e.toString())
                TranslationState.Error
            }
        }
    }

    fun getClipboard(): ClipboardManager {
        return clipboard
    }

    fun getAdditive(id: Int) {
        viewModelScope.launch {
            additive = additivesRepository.getAdditive(id)
            additive?.let { additive ->
                getTranslation(additive.info)
            }
        }
    }

    fun setFavourite(favourite: Int) {
        viewModelScope.launch {
            additivesRepository.setFavourite(additive!!.id, favourite)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                AdditiveDetailViewModel(
                    additivesRepository = application.container.additivesRepository,
                    clipboard = application.container.clipboardManager,
                    transtationRepository = application.container.translationRepository
                )
            }
        }
    }
}
