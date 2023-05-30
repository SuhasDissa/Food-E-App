package app.suhasdissa.foode.backend.models

import kotlinx.serialization.Serializable

@Serializable
class SelectedImage {
    val display: SelectedImageItem? = null
    val small: SelectedImageItem? = null
    val thumb: SelectedImageItem? = null
}