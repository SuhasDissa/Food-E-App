package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(

    @SerialName("nutrient_levels")
    val nutrientLevels: NutrientLevels? = null,

    val nutriments: Nutriments? = null,

    @SerialName("additives_original_tags")
    val additivesOriginalTags: List<String> = emptyList(),

    @SerialName("additives_tags") val additivesTags: List<String> = emptyList(),
    val allergens: String? = null,

    val brands: String? = null,

    val categories: String? = null,

    val code: String? = null,

    val countries: String? = null,

    @SerialName("ecoscore_grade")
    val ecoScore: String? = null,

    @SerialName("generic_name")
    val genericName: String? = null,

    @SerialName("image_small_url") val imageUrl: String? = null,

    @SerialName("ingredients_text") val ingredientsText: String? = null,

    @SerialName("ingredients_text_with_allergens") val ingredientsTextWithAllergens: String? = null,

    val labels: String? = null,

    @SerialName("nova_group")
    val novaGroup: Int? = null,

    @SerialName("nova_groups")
    val novaGroups: String? = null,

    @SerialName("nutriscore_grade")
    val nutriScore: String? = null,

    val origins: String? = null,

    val packaging: String? = null,

    @SerialName("product_name") val productName: String? = null,

    @SerialName("product_quantity")
    val productQuantity: String? = null,

    @SerialName("quantity")
    val quantity: String? = null,

    @SerialName("serving_size")
    val servingSize: String? = null
)
