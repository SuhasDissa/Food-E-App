package app.suhasdissa.foode.backend.database.dao

import androidx.room.*
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BarcodeHistoryDao {
    @Query("SELECT * FROM barcode_history")
    fun getAll(): Flow<List<BarcodeEntity>>

    @Query("SELECT * FROM barcode_history WHERE favourite LIKE 1")
    fun getFav(): Flow<List<BarcodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(barcode: BarcodeEntity)

    @Delete
    fun delete(barcode: BarcodeEntity)
}
