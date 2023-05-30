package openfoodfacts.github.scrachx.openfood.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val fields: List<String> = emptyList(),
    val id: String? = null,
    val images: List<String> = emptyList(),

    @SerialName("import_t")
    val importT: Long = 0,
    val manufacturer: String? = null,
    val name: String? = null,
    val url: String? = null,
)
