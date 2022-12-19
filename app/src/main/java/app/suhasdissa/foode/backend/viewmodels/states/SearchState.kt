package app.suhasdissa.foode.backend.viewmodels.states

import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive

sealed interface SearchState {
    data class Success(val additives: ArrayList<DetailedAdditive>) : SearchState
    object Empty : SearchState
}