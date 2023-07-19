package app.suhasdissa.foode.backend.models.lingva

import kotlinx.serialization.Serializable

@Serializable
data class LVTranslationResponse(
    val translation: String = ""
)
