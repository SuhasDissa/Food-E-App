package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("from_palm_oil")
    val fromPalmOil: String? = null,
    val id: String? = null,
    val origin: String? = null,
    val percent: String? = null,
    val rank: Int = 0,
    val text: String? = null,
    val vegan: String? = null,
    val vegetarian: String? = null,
)