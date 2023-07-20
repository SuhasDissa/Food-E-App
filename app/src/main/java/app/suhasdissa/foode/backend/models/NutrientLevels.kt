package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutrientLevels(
    val fat: String? = null,
    val salt: String? = null,
    @SerialName("saturated-fat")
    val saturatedFat: String? = null,
    val sugars: String? = null
)
