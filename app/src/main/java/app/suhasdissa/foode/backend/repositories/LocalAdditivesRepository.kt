package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.dao.AdditivesDao
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import kotlinx.coroutines.flow.Flow

class LocalAdditivesRepository(private val additivesDao: AdditivesDao) : AdditivesRepository {

    override fun getAdditives(): Flow<List<AdditivesEntity>> = additivesDao.getAll()
    override suspend fun getAdditive(id: Int): AdditivesEntity = additivesDao.getOne(id)
    override suspend fun search(search: String): List<AdditivesEntity> = additivesDao.search(search)
    override fun getFavourites(): Flow<List<AdditivesEntity>> = additivesDao.getFavourites()
    override suspend fun setFavourite(id: Int, favourite: Int) = additivesDao.setFavourite(
        id,
        favourite
    )
}
