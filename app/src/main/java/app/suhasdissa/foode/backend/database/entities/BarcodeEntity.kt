package app.suhasdissa.foode.backend.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode_history")
data class BarcodeEntity(
    @PrimaryKey val barcode: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "favourite", defaultValue = "0") val isFavourite: Boolean = false
) {
    fun toggleLike(): BarcodeEntity {
        return copy(
            isFavourite = !isFavourite
        )
    }
}
