package app.suhasdissa.foode.backend.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenFoodFactsResponse(
    val product: Product? = null,
    val code: String? = null,
    val status: Int = 0 // Boolean
)
