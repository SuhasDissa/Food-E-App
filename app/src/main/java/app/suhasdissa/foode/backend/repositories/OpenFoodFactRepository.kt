package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.api.OpenFoodFactsAPI
import app.suhasdissa.foode.backend.models.Product

interface OpenFoodFactRepository {
    suspend fun getOnlineData(barcode: String): Product?
}

class OpenFoodFactRepositoryImpl : OpenFoodFactRepository {
    override suspend fun getOnlineData(barcode: String): Product? {
        return OpenFoodFactsAPI.retrofitService.getProductData(barcode).product

    }
}