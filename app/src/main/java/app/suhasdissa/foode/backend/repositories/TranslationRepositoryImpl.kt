package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.api.LingvaTranslateAPI
import app.suhasdissa.foode.backend.models.lingva.Language

class TranslationRepositoryImpl : TranslationRepository {
    override suspend fun getLanguages(): List<Language> =
        LingvaTranslateAPI.retrofitService.getLanguages().languages

    override suspend fun getTranslation(target: String, text: String): String =
        LingvaTranslateAPI.retrofitService.translate(target, text).translation
}
