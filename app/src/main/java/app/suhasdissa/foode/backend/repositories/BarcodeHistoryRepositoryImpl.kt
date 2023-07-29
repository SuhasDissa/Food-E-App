package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.dao.BarcodeHistoryDao
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import kotlinx.coroutines.flow.Flow

class BarcodeHistoryRepositoryImpl(private val barcodeHistoryDao: BarcodeHistoryDao) :
    BarcodeHistoryRepository {
    override fun getAll(): Flow<List<BarcodeEntity>> = barcodeHistoryDao.getAll()
    override fun getFav(): Flow<List<BarcodeEntity>> = barcodeHistoryDao.getFav()
    override suspend fun saveBarcode(barcode: BarcodeEntity) = barcodeHistoryDao.insert(barcode)
    override suspend fun delete(barcode: BarcodeEntity) = barcodeHistoryDao.delete(barcode)
}
