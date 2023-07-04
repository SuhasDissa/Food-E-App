package app.suhasdissa.foode.backend.viewmodels.states

import app.suhasdissa.foode.backend.models.Products

sealed interface FFSearchState {
    data class Success(val products: List<Products>) : FFSearchState
    data class Error(val e: String) : FFSearchState
    object NotFound :FFSearchState
    object Empty : FFSearchState
    object Loading : FFSearchState
}