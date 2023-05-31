package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Nutriments(
    val calcium: Float = 0f,

    @SerialName("calcium_value")
    val calciumValue: Float = 0f,

    @SerialName("calcium_100g")
    val calcium100G: Float = 0f,

    @SerialName("calcium_serving")
    val calciumServing: Float = 0f,

    @SerialName("calcium_unit")
    val calciumUnit: String? = null,
    val carbohydrates: Float = 0f,

    @SerialName("carbohydrates_value")
    val carbohydratesValue: Float = 0f,

    @SerialName("carbohydrates_100g")
    val carbohydrates100G: Float = 0f,

    @SerialName("carbohydrates_serving")
    val carbohydratesServing: Float = 0f,

    @SerialName("carbohydrates_unit")
    val carbohydratesUnit: String? = null,

    @SerialName("carbon-footprint-from-known-ingredients_product")
    val carbonFootprintFromKnownIngredientsProduct: Float = 0f,

    @SerialName("carbon-footprint-from-known-ingredients_100g")
    val carbonFootprintFromKnownIngredients100G: Float = 0f,

    @SerialName("carbon-footprint-from-known-ingredients_serving")
    val carbonFootprintFromKnownIngredientsServing: Float = 0f,
    val cholesterol: Float = 0f,

    @SerialName("cholesterol_value")
    val cholesterolValue: Float = 0f,

    @SerialName("cholesterol_100g")
    val cholesterol100G: Float = 0f,

    @SerialName("cholesterol_serving")
    val cholesterolServing: Float = 0f,

    @SerialName("cholesterol_unit")
    val cholesterolUnit: String? = null,
    val energy: Int = 0,

    @SerialName("energy-kcal")
    val energyKcal: Int = 0,

    @SerialName("energy-kj")
    val energyKj: Int = 0,

    @SerialName("energy_value")
    val energyValue: Int = 0,

    @SerialName("energy-kcal_value")
    val energyKcalValue: Int = 0,

    @SerialName("energy-kj_value")
    val energyKjValue: Int = 0,

    @SerialName("energy_100g")
    val energy100G: Int = 0,

    @SerialName("energy-kcal_100g")
    val energyKcal100G: Int = 0,

    @SerialName("energy-kj_100g")
    val energyKj100G: Int = 0,

    @SerialName("energy_serving")
    val energyServing: Int = 0,

    @SerialName("energy-kcal_serving")
    val energyKcalServing: Double = 0.0,

    @SerialName("energy-kj_serving")
    val energyKjServing: Int = 0,

    @SerialName("energy_unit")
    val energyUnit: String? = null,

    @SerialName("energy-kcal_unit")
    val energyKcalUnit: String? = null,

    @SerialName("energy-kj_unit")
    val energyKjUnit: String? = null,
    val fat: Float = 0f,

    @SerialName("fat_value")
    val fatValue: Float = 0f,

    @SerialName("fat_100g")
    val fat100G: Float = 0f,

    @SerialName("fat_serving")
    val fatServing: Float = 0f,

    @SerialName("fat_unit")
    val fatUnit: String? = null,
    val fiber: Float = 0f,

    @SerialName("fiber_value")
    val fiberValue: Float = 0f,

    @SerialName("fiber_100g")
    val fiber100G: Float = 0f,

    @SerialName("fiber_serving")
    val fiberServing: Float = 0f,

    @SerialName("fiber_unit")
    val fiberUnit: String? = null,

    @SerialName("fruits-vegetables-nuts-estimate-from-ingredients_100g")
    val fruitsVegetablesNutsEstimateFromIngredients100G: Float = 0f,
    val iron: Float = 0f,

    @SerialName("iron_value")
    val ironValue: Float = 0f,

    @SerialName("iron_100g")
    val iron100G: Float = 0f,

    @SerialName("iron_serving")
    val ironServing: Float = 0f,

    @SerialName("iron_unit")
    val ironUnit: String? = null,

    @SerialName("nova-group")
    val novaGroup: Float = 0f,

    @SerialName("nova-group_100g")
    val novaGroup100G: Float = 0f,

    @SerialName("nova-group_serving")
    val novaGroupServing: Float = 0f,
    val proteins: Float = 0f,

    @SerialName("proteins_value")
    val proteinsValue: Float = 0f,

    @SerialName("proteins_100g")
    val proteins100G: Float = 0f,

    @SerialName("proteins_serving")
    val proteinsServing: Float = 0f,

    @SerialName("proteins_unit")
    val proteinsUnit: String? = null,
    val salt: Float = 0f,

    @SerialName("salt_value")
    val saltValue: Float = 0f,

    @SerialName("salt_100g")
    val salt100G: Float = 0f,

    @SerialName("salt_serving")
    val saltServing: Float = 0f,

    @SerialName("salt_unit")
    val saltUnit: String? = null,

    @SerialName("saturated-fat")
    val saturatedFat: Float = 0f,

    @SerialName("saturated-fat_value")
    val saturatedFatValue: Float = 0f,

    @SerialName("saturated-fat_100g")
    val saturatedFat100G: Float = 0f,

    @SerialName("saturated-fat_serving")
    val saturatedFatServing: Float = 0f,

    @SerialName("saturated-fat_unit")
    val saturatedFatUnit: String? = null,
    val sodium: Float = 0f,

    @SerialName("sodium_value")
    val sodiumValue: Float = 0f,

    @SerialName("sodium_100g")
    val sodium100G: Float = 0f,

    @SerialName("sodium_serving")
    val sodiumServing: Float = 0f,

    @SerialName("sodium_unit")
    val sodiumUnit: String? = null,
    val sugars: Float = 0f,

    @SerialName("sugars_value")
    val sugarsValue: Float = 0f,

    @SerialName("sugars_100g")
    val sugars100G: Float = 0f,

    @SerialName("sugars_serving")
    val sugarsServing: Float = 0f,

    @SerialName("sugars_unit")
    val sugarsUnit: String? = null,

    @SerialName("trans-fat")
    val transFat: Float = 0f,

    @SerialName("trans-fat_value")
    val transFatValue: Float = 0f,

    @SerialName("trans-fat_100g")
    val transFat100G: Float = 0f,

    @SerialName("trans-fat_serving")
    val transFatServing: Float = 0f,

    @SerialName("trans-fat_unit")
    val transFatUnit: String? = null,

    @SerialName("vitamin-a")
    val vitaminA: Float = 0f,

    @SerialName("vitamin-a_value")
    val vitaminAValue: Float = 0f,

    @SerialName("vitamin-a_100g")
    val vitaminA100G: Float = 0f,

    @SerialName("vitamin-a_serving")
    val vitaminAServing: Float = 0f,

    @SerialName("vitamin-a_unit")
    val vitaminAUnit: String? = null,

    @SerialName("vitamin-c")
    val vitaminC: Float = 0f,

    @SerialName("vitamin-c_value")
    val vitaminCValue: Float = 0f,

    @SerialName("vitamin-c_100g")
    val vitaminC100G: Float = 0f,

    @SerialName("vitamin-c_serving")
    val vitaminCServing: Float = 0f,

    @SerialName("vitamin-c_unit")
    val vitaminCUnit: String? = null,

    @SerialName("vitamin-d")
    val vitaminD: Float = 0f,

    @SerialName("vitamin-d_value")
    val vitaminDValue: Float = 0f,

    @SerialName("vitamin-d_100g")
    val vitaminD100G: Float = 0f,

    @SerialName("vitamin-d_serving")
    val vitaminDServing: Float = 0f,

    @SerialName("vitamin-d_unit")
    val vitaminDUnit: String? = null,
) {
}
