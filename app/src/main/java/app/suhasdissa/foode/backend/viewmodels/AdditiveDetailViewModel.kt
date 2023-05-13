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
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive
import kotlinx.coroutines.launch

class AdditiveDetailViewModel(private val additivesRepository: AdditivesRepository) : ViewModel() {
    var additive: DetailedAdditive by mutableStateOf(
        DetailedAdditive(0, "", "", "", "")
    )
        private set


    fun getAdditive(id: Int) {
        viewModelScope.launch {
            additive = additivesRepository.getAdditive(id)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                val songRepository = application.container.additivesRepository
                AdditiveDetailViewModel(additivesRepository = songRepository)
            }
        }
    }
}