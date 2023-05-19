package app.suhasdissa.foode.backend.viewmodels

import android.content.ClipboardManager
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
import kotlinx.coroutines.launch

class AdditiveDetailViewModel(
    private val additivesRepository: AdditivesRepository,
    private val clipboard: ClipboardManager
) : ViewModel() {
    var additive: AdditivesEntity by mutableStateOf(
        AdditivesEntity(0, "", "", "", "", "", 0)
    )
        private set

    fun getClipboard(): ClipboardManager {
        return clipboard
    }

    fun getAdditive(id: Int) {
        viewModelScope.launch {
            additive = additivesRepository.getAdditive(id)
        }
    }

    fun setFavourite(favourite: Int) {
        viewModelScope.launch {
            additivesRepository.setFavourite(additive.id, favourite)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                val additivesRepository = application.container.additivesRepository
                val clipboard = application.container.clipboardManager
                AdditiveDetailViewModel(
                    additivesRepository = additivesRepository,
                    clipboard = clipboard
                )
            }
        }
    }
}