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
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import kotlinx.coroutines.launch

class MainAdditivesViewModel(private val additivesRepository: AdditivesRepository) : ViewModel() {
    var additives: List<AdditivesEntity> by mutableStateOf(listOf())
        private set
    var favAdditives: List<AdditivesEntity> by mutableStateOf(listOf())
        private set

    init {
        getAdditives()
        getFavouriteAdditives()
    }

    private fun getFavouriteAdditives() {
        viewModelScope.launch {
            favAdditives = additivesRepository.getFavourites()
        }
    }

    private fun getAdditives() {
        viewModelScope.launch {
            additives = additivesRepository.getAdditives()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                val songRepository = application.container.additivesRepository
                MainAdditivesViewModel(additivesRepository = songRepository)
            }
        }
    }
}