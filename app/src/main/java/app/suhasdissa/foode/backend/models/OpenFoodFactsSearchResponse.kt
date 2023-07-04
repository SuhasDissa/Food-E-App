package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenFoodFactsSearchResponse(
    @SerialName("products") var products: ArrayList<Products> = arrayListOf(),
)

@Serializable
data class Products(
    @SerialName("brands") var brands: String? = null,
    @SerialName("code") var code: String,
    @SerialName("image_thumb_url") var imageThumbUrl: String? = null,
    @SerialName("product_name") var productName: String? = null
)