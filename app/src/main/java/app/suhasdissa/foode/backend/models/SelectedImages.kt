package openfoodfacts.github.scrachx.openfood.api.model

import app.suhasdissa.foode.backend.models.SelectedImage
import kotlinx.serialization.Serializable

@Serializable
data class SelectedImages(
    private val front: SelectedImage? = null,
    private val ingredients: SelectedImage? = null,
    private val nutrition: SelectedImage? = null,
)
