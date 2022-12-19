package app.suhasdissa.foode.backend.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "additives")
data class AdditivesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "e_code") val eCode: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "e_type") val eType: String

)