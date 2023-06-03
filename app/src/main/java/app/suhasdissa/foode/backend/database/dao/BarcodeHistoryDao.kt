package app.suhasdissa.foode.backend.database.dao

import androidx.room.*
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity

@Dao
interface BarcodeHistoryDao {
    @Query("SELECT * FROM barcode_history")
    fun getAll(): List<BarcodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(barcode: BarcodeEntity)

    @Delete
    fun delete(barcode: BarcodeEntity)
}