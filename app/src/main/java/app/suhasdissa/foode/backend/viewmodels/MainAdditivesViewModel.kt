package app.suhasdissa.foode.backend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.suhasdissa.foode.FoodeApplication
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainAdditivesViewModel(additivesRepository: AdditivesRepository) : ViewModel() {

    val additives: StateFlow<List<AdditivesEntity>> = additivesRepository.getAdditives().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf()
    )
    val favAdditives: StateFlow<List<AdditivesEntity>> =
        additivesRepository.getFavourites().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                MainAdditivesViewModel(application.container.additivesRepository)
            }
        }
    }
}
