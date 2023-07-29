package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import kotlinx.coroutines.flow.Flow

interface AdditivesRepository {
    fun getAdditives(): Flow<List<AdditivesEntity>>
    suspend fun getAdditive(id: Int): AdditivesEntity
    suspend fun search(search: String): List<AdditivesEntity>
    fun getFavourites(): Flow<List<AdditivesEntity>>
    suspend fun setFavourite(id: Int, favourite: Int)
}
