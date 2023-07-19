package app.suhasdissa.foode.backend.models.lingva

import kotlinx.serialization.Serializable

@Serializable
data class LvLanguage(
    val languages: List<Language> = emptyList()
)
