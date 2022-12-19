package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.dao.AdditivesDao
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive

class LocalAdditivesRepository(private val additivesDao: AdditivesDao) : AdditivesRepository {

    private fun AdditivesEntity.toDetailedAdditive() = DetailedAdditive(
        id = id,
        eCode = eCode,
        title = title,
        info = info,
        eType = eType
    )


    override suspend fun getAdditives(): ArrayList<DetailedAdditive> {
        return additivesDao.getAll().mapTo(ArrayList()) {
            it.toDetailedAdditive()
        }
    }

    override suspend fun getAdditive(id: Int): DetailedAdditive {
        return additivesDao.getOne(id).toDetailedAdditive()
    }

    override suspend fun search(search: String): ArrayList<DetailedAdditive> {
        return additivesDao.search(search).mapTo(ArrayList()) {
            it.toDetailedAdditive()
        }
    }
}