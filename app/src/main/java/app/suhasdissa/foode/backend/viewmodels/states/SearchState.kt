package app.suhasdissa.foode.backend.viewmodels.states

import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

sealed interface SearchState {
    data class Success(val additives: List<AdditivesEntity>) : SearchState
    object Empty : SearchState
}