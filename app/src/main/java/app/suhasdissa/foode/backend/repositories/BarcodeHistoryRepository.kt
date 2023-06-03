package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.database.entities.BarcodeEntity

interface BarcodeHistoryRepository {

    suspend fun getAll(): List<BarcodeEntity>

    suspend fun saveBarcode(barcode: BarcodeEntity)
}