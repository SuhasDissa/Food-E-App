package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.models.lingva.Language

interface TranslationRepository {
    suspend fun getLanguages(): List<Language>

    suspend fun getTranslation(target: String, text: String): String
}
