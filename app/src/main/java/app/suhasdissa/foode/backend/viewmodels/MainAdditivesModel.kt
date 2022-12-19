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

class MainAdditivesModel(private val additivesRepository: AdditivesRepository) : ViewModel() {
    var additives: ArrayList<DetailedAdditive> by mutableStateOf(arrayListOf())
        private set

    init {
        getSongs()
    }

    private fun getSongs() {
        viewModelScope.launch {
            additives = additivesRepository.getAdditives()


        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FoodeApplication)
                val songRepository = application.container.additivesRepository
                MainAdditivesModel(additivesRepository = songRepository)
            }
        }
    }
}