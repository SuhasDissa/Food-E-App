package app.suhasdissa.foode.backend.models.lingva

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val code: String = "",
    val name: String = ""
)
