package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.dao.AdditivesDao
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

class LocalAdditivesRepository(private val additivesDao: AdditivesDao) : AdditivesRepository {

    override suspend fun getAdditives(): List<AdditivesEntity> {
        return additivesDao.getAll()
    }

    override suspend fun getAdditive(id: Int): AdditivesEntity {
        return additivesDao.getOne(id)
    }

    override suspend fun search(search: String): List<AdditivesEntity> {
        return additivesDao.search(search)
    }

    override suspend fun getFavourites(): List<AdditivesEntity> {
        return additivesDao.getFavourites()
    }

    override suspend fun setFavourite(id: Int, favourite: Int) {
        additivesDao.setFavourite(id, favourite)
    }
}
