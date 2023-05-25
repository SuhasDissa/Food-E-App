package app.suhasdissa.foode.backend.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.suhasdissa.foode.backend.database.dao.AdditivesDao
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity

@Database(entities = [AdditivesEntity::class], version = 4, exportSchema = false)
abstract class ENumberDatabase : RoomDatabase() {

    abstract fun additivesDao(): AdditivesDao

    companion object {
        @Volatile
        private var INSTANCE: ENumberDatabase? = null

        fun getDatabase(context: Context): ENumberDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, ENumberDatabase::class.java, "database"
                ).createFromAsset("databases/database.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}