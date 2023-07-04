package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.api.OpenFoodFactsAPI
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.models.Products

interface OpenFoodFactRepository {
    suspend fun getOnlineData(barcode: String): Product?

    suspend fun searchProduct(q: String): ArrayList<Products>
}

class OpenFoodFactRepositoryImpl : OpenFoodFactRepository {
    override suspend fun getOnlineData(barcode: String): Product? {
        return OpenFoodFactsAPI.retrofitService.getProductData(barcode).product

    }

    override suspend fun searchProduct(q: String): ArrayList<Products> {
        return OpenFoodFactsAPI.retrofitService.searchProduct(q).products
    }
}