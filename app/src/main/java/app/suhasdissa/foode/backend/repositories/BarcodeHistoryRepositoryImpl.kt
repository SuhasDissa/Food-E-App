package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.dao.BarcodeHistoryDao
import app.suhasdissa.foode.backend.database.entities.BarcodeEntity

class BarcodeHistoryRepositoryImpl(private val barcodeHistoryDao: BarcodeHistoryDao) :
    BarcodeHistoryRepository {
    override suspend fun getAll(): List<BarcodeEntity> {
        return barcodeHistoryDao.getAll()
    }

    override suspend fun saveBarcode(barcode: BarcodeEntity) {
        barcodeHistoryDao.insert(barcode)
    }
}