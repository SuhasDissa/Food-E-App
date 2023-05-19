package app.suhasdissa.foode.backend.database.dao

import androidx.room.*
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

@Dao
interface AdditivesDao {
    @Query("SELECT * FROM additives")
    fun getAll(): List<AdditivesEntity>

    @Query("SELECT * FROM additives WHERE e_code LIKE :search OR title LIKE :search")
    fun search(search: String): List<AdditivesEntity>

    @Query("SELECT * FROM additives WHERE favourite LIKE 1")
    fun getFavourites(): List<AdditivesEntity>

    @Query("UPDATE additives SET favourite = :favourite WHERE id = :id")
    fun setFavourite(id: Int, favourite: Int)

    @Query("SELECT * FROM additives WHERE id = :id")
    fun getOne(id: Int): AdditivesEntity
}