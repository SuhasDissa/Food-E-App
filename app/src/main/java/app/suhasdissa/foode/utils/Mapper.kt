package app.suhasdissa.foode.utils

import app.suhasdissa.foode.backend.database.entities.BarcodeEntity
import app.suhasdissa.foode.backend.models.Products

fun Products.toBarcodeEntity(): BarcodeEntity {
    return BarcodeEntity(
        barcode = this.code,
        title = this.productName ?: "",
        imageUrl = this.imageThumbUrl ?: ""
    )
}
