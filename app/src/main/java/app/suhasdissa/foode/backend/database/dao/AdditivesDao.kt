package app.suhasdissa.foode.backend.database.dao

import androidx.room.*
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AdditivesDao {
    @Query("SELECT * FROM additives")
    fun getAll(): Flow<List<AdditivesEntity>>

    @Query("SELECT * FROM additives WHERE e_code LIKE :search OR title LIKE :search")
    fun search(search: String): List<AdditivesEntity>

    @Query("SELECT * FROM additives WHERE favourite LIKE 1")
    fun getFavourites(): Flow<List<AdditivesEntity>>

    @Query("UPDATE additives SET favourite = :favourite WHERE id = :id")
    fun setFavourite(id: Int, favourite: Int)

    @Query("SELECT * FROM additives WHERE id = :id")
    fun getOne(id: Int): AdditivesEntity
}
