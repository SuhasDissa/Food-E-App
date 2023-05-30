package app.suhasdissa.foode.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import openfoodfacts.github.scrachx.openfood.api.model.*

@Serializable
data class Product(
    val images: Images? = null,
    val ingredients: List<Ingredient> = emptyList(),

    @SerialName("languages_codes")
    val languagesCodes: LanguagesCodes? = null,

    @SerialName("nutrient_levels")
    val nutrientLevels: NutrientLevels? = null,
    val nutriments: Nutriments? = null,

    @SerialName("selected_images")
    val selectedImages: SelectedImages? = null,
    val sources: List<Source> = emptyList(),

    @SerialName("additives_n")
    val additivesN: Int = 0,

    @SerialName("additives_old_n")
    val additivesOldN: Int = 0,

    @SerialName("additives_original_tags")
    val additivesOriginalTags: List<String> = emptyList(),

    @SerialName("additives_old_tags")
    val additivesOldTags: List<String> = emptyList(),

    @SerialName("additives_prev_original_tags")
    val additivesPrevOriginalTags: List<String> = emptyList(),

    @SerialName("additives_debug_tags")
    val additivesDebugTags: List<String> = emptyList(),

    @SerialName("additives_tags")
    val additivesTags: List<String> = emptyList(),
    val allergens: String? = null,

    @SerialName("allergens_from_ingredients")
    val allergensFromIngredients: String? = null,

    @SerialName("allergens_from_user")
    val allergensFromUser: String? = null,

    @SerialName("allergens_hierarchy")
    val allergensHierarchy: List<String> = emptyList(),

    @SerialName("allergens_lc")
    val allergensLc: String? = null,

    @SerialName("allergens_tags")
    val allergensTags: List<String> = emptyList(),

    @SerialName("amino_acids_prev_tags")
    val aminoAcidsPrevTags: List<String> = emptyList(),

    @SerialName("amino_acids_tags")
    val aminoAcidsTags: List<String> = emptyList(),
    val brands: String? = null,

    @SerialName("brands_debug_tags")
    val brandsDebugTags: List<String> = emptyList(),

    @SerialName("brands_tags")
    val brandsTags: List<String> = emptyList(),

    @SerialName("carbon_footprint_percent_of_known_ingredients")
    val carbonFootprintPercentOfKnownIngredients: String? = null,

    @SerialName("carbon_footprint_from_known_ingredients_debug")
    val carbonFootprintFromKnownIngredientsDebug: String? = null,
    val categories: String? = null,

    @SerialName("categories_hierarchy")
    val categoriesHierarchy: List<String> = emptyList(),

    @SerialName("categories_lc")
    val categoriesLc: String? = null,

    @SerialName("categories_properties_tags")
    val categoriesPropertiesTags: List<String> = emptyList(),

    @SerialName("categories_tags")
    val categoriesTags: List<String> = emptyList(),

    @SerialName("checkers_tags")
    val checkersTags: List<String> = emptyList(),

    @SerialName("cities_tags")
    val citiesTags: List<String> = emptyList(),
    val code: String? = null,

    @SerialName("codes_tags")
    val codesTags: List<String> = emptyList(),

    @SerialName("compared_to_category")
    val comparedToCategory: String? = null,
    val complete: Int = 0,

    @SerialName("completed_t")
    val completedT: Long = 0,
    val completeness: Double = 0.0,

    @SerialName("conservation_conditions")
    val conservationConditions: String? = null,
    val countries: String? = null,

    @SerialName("countries_hierarchy")
    val countriesHierarchy: List<String> = emptyList(),

    @SerialName("countries_lc")
    val countriesLc: String? = null,

    @SerialName("countries_debug_tags")
    val countriesDebugTags: List<String> = emptyList(),

    @SerialName("countries_tags")
    val countriesTags: List<String> = emptyList(),

    @SerialName("correctors_tags")
    val correctorsTags: List<String> = emptyList(),

    @SerialName("created_t")
    val createdT: Long = 0,
    val creator: String? = null,

    @SerialName("data_quality_bugs_tags")
    val dataQualityBugsTags: List<String> = emptyList(),

    @SerialName("data_quality_errors_tags")
    val dataQualityErrorsTags: List<String> = emptyList(),

    @SerialName("data_quality_info_tags")
    val dataQualityInfoTags: List<String> = emptyList(),

    @SerialName("data_quality_tags")
    val dataQualityTags: List<String> = emptyList(),

    @SerialName("data_quality_warnings_tags")
    val dataQualityWarningsTags: List<String> = emptyList(),

    @SerialName("data_sources")
    val dataSources: String? = null,

    @SerialName("data_sources_tags")
    val dataSourcesTags: List<String> = emptyList(),

    @SerialName("debug_param_sorted_langs")
    val debugParamSortedLangs: List<String> = emptyList(),

    @SerialName("editors_tags")
    val editorsTags: List<String> = emptyList(),

    @SerialName("emb_codes")
    val embCodes: String? = null,

    @SerialName("emb_codes_debug_tags")
    val embCodesDebugTags: List<String> = emptyList(),

    @SerialName("emb_codes_orig")
    val embCodesOrig: String? = null,

    @SerialName("emb_codes_tags")
    val embCodesTags: List<String> = emptyList(),

    @SerialName("entry_dates_tags")
    val entryDatesTags: List<String> = emptyList(),

    @SerialName("expiration_date")
    val expirationDate: String? = null,

    @SerialName("expiration_date_debug_tags")
    val expirationDateDebugTags: List<String> = emptyList(),

    @SerialName("fruits-vegetables-nuts_100g_estimate")
    val fruitsVegetablesNuts100GEstimate: Int = 0,

    @SerialName("generic_name")
    val genericName: String? = null,
    val id: String? = null,
    val _id: String? = null,

    @SerialName("image_front_small_url")
    val imageFrontSmallUrl: String? = null,

    @SerialName("image_front_thumb_url")
    val imageFrontThumbUrl: String? = null,

    @SerialName("image_front_url")
    val imageFrontUrl: String? = null,

    @SerialName("image_ingredients_url")
    val imageIngredientsUrl: String? = null,

    @SerialName("image_ingredients_small_url")
    val imageIngredientsSmallUrl: String? = null,

    @SerialName("image_ingredients_thumb_url")
    val imageIngredientsThumbUrl: String? = null,

    @SerialName("image_nutrition_small_url")
    val imageNutritionSmallUrl: String? = null,

    @SerialName("image_nutrition_thumb_url")
    val imageNutritionThumbUrl: String? = null,

    @SerialName("image_nutrition_url")
    val imageNutritionUrl: String? = null,

    @SerialName("image_small_url")
    val imageSmallUrl: String? = null,

    @SerialName("image_thumb_url")
    val imageThumbUrl: String? = null,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("informers_tags")
    val informersTags: List<String> = emptyList(),

    @SerialName("ingredients_analysis_tags")
    val ingredientsAnalysisTags: List<String> = emptyList(),

    @SerialName("ingredients_debug")
    val ingredientsDebug: List<String?> = emptyList(),

    @SerialName("ingredients_from_or_that_may_be_from_palm_oil_n")
    val ingredientsFromOrThatMayBeFromPalmOilN: Int = 0,

    @SerialName("ingredients_from_palm_oil_tags")
    val ingredientsFromPalmOilTags: List<String> = emptyList(),

    @SerialName("ingredients_from_palm_oil_n")
    val ingredientsFromPalmOilN: Int = 0,

    @SerialName("ingredients_hierarchy")
    val ingredientsHierarchy: List<String> = emptyList(),

    @SerialName("ingredients_ids_debug")
    val ingredientsIdsDebug: List<String> = emptyList(),

    @SerialName("ingredients_n")
    val ingredientsN: Int = 0,

    @SerialName("ingredients_n_tags")
    val ingredientsNTags: List<String> = emptyList(),

    @SerialName("ingredients_original_tags")
    val ingredientsOriginalTags: List<String> = emptyList(),

    @SerialName("ingredients_tags")
    val ingredientsTags: List<String> = emptyList(),

    @SerialName("ingredients_text")
    val ingredientsText: String? = null,

    @SerialName("ingredients_text_debug")
    val ingredientsTextDebug: String? = null,

    @SerialName("ingredients_text_with_allergens")
    val ingredientsTextWithAllergens: String? = null,

    @SerialName("ingredients_that_may_be_from_palm_oil_n")
    val ingredientsThatMayBeFromPalmOilN: Int = 0,

    @SerialName("ingredients_that_may_be_from_palm_oil_tags")
    val ingredientsThatMayBeFromPalmOilTags: List<String> = emptyList(),

    @SerialName("interface_version_created")
    val interfaceVersionCreated: String? = null,

    @SerialName("interface_version_modified")
    val interfaceVersionModified: String? = null,

    @SerialName("_keywords")
    val keywords: List<String> = emptyList(),

    @SerialName("known_ingredients_n")
    val knownIngredientsN: Int = 0,
    val labels: String? = null,

    @SerialName("labels_hierarchy")
    val labelsHierarchy: List<String> = emptyList(),

    @SerialName("labels_lc")
    val labelsLc: String? = null,

    @SerialName("labels_prev_hierarchy")
    val labelsPrevHierarchy: List<String> = emptyList(),

    @SerialName("labels_prev_tags")
    val labelsPrevTags: List<String> = emptyList(),

    @SerialName("labels_tags")
    val labelsTags: List<String> = emptyList(),

    @SerialName("labels_debug_tags")
    val labelsDebugTags: List<String> = emptyList(),
    val lang: String? = null,

    @SerialName("lang_debug_tags")
    val langDebugTags: List<String> = emptyList(),

    @SerialName("languages_hierarchy")
    val languagesHierarchy: List<String> = emptyList(),

    @SerialName("languages_tags")
    val languagesTags: List<String> = emptyList(),

    @SerialName("last_edit_dates_tags")
    val lastEditDatesTags: List<String> = emptyList(),

    @SerialName("last_editor")
    val lastEditor: String? = null,

    @SerialName("last_image_dates_tags")
    val lastImageDatesTags: List<String> = emptyList(),

    @SerialName("last_image_t")
    val lastImageT: Long = 0,

    @SerialName("last_modified_by")
    val lastModifiedBy: String? = null,

    @SerialName("last_modified_t")
    val lastModifiedT: Long = 0,
    val lc: String? = null,
    val link: String? = null,

    @SerialName("link_debug_tags")
    val linkDebugTags: List<String> = emptyList(),

    @SerialName("manufacturing_places")
    val manufacturingPlaces: String? = null,

    @SerialName("manufacturing_places_debug_tags")
    val manufacturingPlacesDebugTags: List<String> = emptyList(),

    @SerialName("manufacturing_places_tags")
    val manufacturingPlacesTags: List<String> = emptyList(),

    @SerialName("max_imgid")
    val maxImgid: String? = null,

    @SerialName("minerals_prev_tags")
    val mineralsPrevTags: List<String> = emptyList(),

    @SerialName("minerals_tags")
    val mineralsTags: List<String> = emptyList(),

    @SerialName("misc_tags")
    val miscTags: List<String> = emptyList(),

    @SerialName("net_weight_unit")
    val netWeightUnit: String? = null,

    @SerialName("net_weight_value")
    val netWeightValue: String? = null,

    @SerialName("nutrition_data_per")
    val nutritionDataPer: String? = null,

    @SerialName("nutrition_score_warning_no_fruits_vegetables_nuts")
    val nutritionScoreWarningNoFruitsVegetablesNuts: Int = 0,

    @SerialName("no_nutrition_data")
    val noNutritionData: String? = null,

    @SerialName("nova_group")
    val novaGroup: String? = null,

    @SerialName("nova_groups")
    val novaGroups: String? = null,

    @SerialName("nova_group_debug")
    val novaGroupDebug: String? = null,

    @SerialName("nova_group_tags")
    val novaGroupTags: List<String> = emptyList(),

    @SerialName("nova_groups_tags")
    val novaGroupsTags: List<String> = emptyList(),

    @SerialName("nucleotides_prev_tags")
    val nucleotidesPrevTags: List<String> = emptyList(),

    @SerialName("nucleotides_tags")
    val nucleotidesTags: List<String> = emptyList(),

    @SerialName("nutrient_levels_tags")
    val nutrientLevelsTags: List<String> = emptyList(),

    @SerialName("nutrition_data")
    val nutritionData: String? = null,

    @SerialName("nutrition_data_per_debug_tags")
    val nutritionDataPerDebugTags: List<String> = emptyList(),

    @SerialName("nutrition_data_prepared")
    val nutritionDataPrepared: String? = null,

    @SerialName("nutrition_data_prepared_per")
    val nutritionDataPreparedPer: String? = null,

    @SerialName("nutrition_grades")
    val nutritionGrades: String? = null,

    @SerialName("nutrition_score_beverage")
    val nutritionScoreBeverage: Int = 0,

    @SerialName("nutrition_score_debug")
    val nutritionScoreDebug: String? = null,

    @SerialName("nutrition_score_warning_no_fiber")
    val nutritionScoreWarningNoFiber: Int = 0,

    @SerialName("nutrition_grades_tags")
    val nutritionGradesTags: List<String> = emptyList(),
    val origins: String? = null,

    @SerialName("origins_debug_tags")
    val originsDebugTags: List<String> = emptyList(),

    @SerialName("origins_tags")
    val originsTags: List<String> = emptyList(),

    @SerialName("other_information")
    val otherInformation: String? = null,

    @SerialName("other_nutritional_substances_tags")
    val otherNutritionalSubstancesTags: List<String> = emptyList(),
    val packaging: String? = null,

    @SerialName("packaging_debug_tags")
    val packagingDebugTags: List<String> = emptyList(),

    @SerialName("packaging_tags")
    val packagingTags: List<String> = emptyList(),

    @SerialName("photographers_tags")
    val photographersTags: List<String> = emptyList(),

    @SerialName("pnns_groups_1")
    val pnnsGroups1: String? = null,

    @SerialName("pnns_groups_2")
    val pnnsGroups2: String? = null,

    @SerialName("pnns_groups_1_tags")
    val pnnsGroups1Tags: List<String> = emptyList(),

    @SerialName("pnns_groups_2_tags")
    val pnnsGroups2Tags: List<String> = emptyList(),

    @SerialName("popularity_key")
    val popularityKey: Long = 0,

    @SerialName("producer_version_id")
    val producerVersionId: String? = null,

    @SerialName("product_name")
    val productName: String? = null,

    @SerialName("product_quantity")
    val productQuantity: String? = null,

    @SerialName("purchase_places")
    val purchasePlaces: String? = null,

    @SerialName("purchase_places_debug_tags")
    val purchasePlacesDebugTags: List<String> = emptyList(),

    @SerialName("purchase_places_tags")
    val purchasePlacesTags: List<String> = emptyList(),

    @SerialName("quality_tags")
    val qualityTags: List<String> = emptyList(),
    val quantity: String? = null,

    @SerialName("quantity_debug_tags")
    val quantityDebugTags: List<String> = emptyList(),

    @SerialName("recycling_instructions_to_discard")
    val recyclingInstructionsToDiscard: String? = null,
    val rev: Int = 0,

    @SerialName("serving_quantity")
    val servingQuantity: String? = null,

    @SerialName("serving_size")
    val servingSize: String? = null,

    @SerialName("serving_size_debug_tags")
    val servingSizeDebugTags: List<String> = emptyList(),
    val sortkey: Long = 0,
    val states: String? = null,

    @SerialName("states_hierarchy")
    val statesHierarchy: List<String> = emptyList(),

    @SerialName("states_tags")
    val statesTags: List<String> = emptyList(),
    val stores: String? = null,

    @SerialName("stores_debug_tags")
    val storesDebugTags: List<String> = emptyList(),

    @SerialName("stores_tags")
    val storesTags: List<String> = emptyList(),
    val traces: String? = null,

    @SerialName("traces_from_ingredients")
    val tracesFromIngredients: String? = null,

    @SerialName("traces_hierarchy")
    val tracesHierarchy: List<String> = emptyList(),

    @SerialName("traces_debug_tags")
    val tracesDebugTags: List<String> = emptyList(),

    @SerialName("traces_from_user")
    val tracesFromUser: String? = null,

    @SerialName("traces_lc")
    val tracesLc: String? = null,

    @SerialName("traces_tags")
    val tracesTags: List<String> = emptyList(),

    @SerialName("unknown_ingredients_n")
    val unknownIngredientsN: Int = 0,

    @SerialName("unknown_nutrients_tags")
    val unknownNutrientsTags: List<String> = emptyList(),

    @SerialName("update_key")
    val updateKey: String? = null,

    @SerialName("vitamins_prev_tags")
    val vitaminsPrevTags: List<String> = emptyList(),

    @SerialName("vitamins_tags")
    val vitaminsTags: List<String> = emptyList(),
    //var other: MutableMap<String, JsonElement> = LinkedHashMap(),
) {
//    fun setDetail(key: String, value: JsonElement) {
//        other[key] = value
//    }
}