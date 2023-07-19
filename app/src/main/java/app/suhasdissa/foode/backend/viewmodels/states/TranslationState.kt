package app.suhasdissa.foode.backend.viewmodels.states

sealed interface TranslationState {
    data class Success(val translation: String) : TranslationState
    object Error : TranslationState
    object Loading : TranslationState
    object NotTranslated : TranslationState
}
