package app.suhasdissa.foode.backend.viewmodels.states

sealed interface TranslationState {
    object Success : TranslationState
    object Error : TranslationState
    object Loading : TranslationState
    object NotTranslated : TranslationState
    object NotSupported : TranslationState
}
