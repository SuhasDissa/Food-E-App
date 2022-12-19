package app.suhasdissa.foode

import android.app.Application
import app.suhasdissa.foode.backend.database.ENumberDatabase

class FoodeApplication : Application() {
    private val database by lazy { ENumberDatabase.getDatabase(this) }
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(database)
    }
}