package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import kotlinx.coroutines.flow.Flow

interface BarcodeHistoryRepository {

    fun getAll(): Flow<List<BarcodeEntity>>
    fun getFav(): Flow<List<BarcodeEntity>>
    suspend fun saveBarcode(barcode: BarcodeEntity)
    suspend fun delete(barcode: BarcodeEntity)
}
