package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

interface AdditivesRepository {
    suspend fun getAdditives(): List<AdditivesEntity>
    suspend fun getAdditive(id: Int): AdditivesEntity
    suspend fun search(search: String): List<AdditivesEntity>
}