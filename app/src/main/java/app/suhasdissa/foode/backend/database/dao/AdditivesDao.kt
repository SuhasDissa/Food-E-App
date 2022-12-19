package app.suhasdissa.foode.backend.database.dao

import androidx.room.*
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

@Dao
interface AdditivesDao {
    @Query("SELECT * FROM additives")
    fun getAll(): List<AdditivesEntity>

    @Query("SELECT * FROM additives WHERE e_code LIKE :search OR title LIKE :search")
    fun search(search: String): List<AdditivesEntity>

    @Query("SELECT * FROM additives WHERE id = :id")
    fun getOne(id: Int): AdditivesEntity
}