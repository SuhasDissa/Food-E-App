package app.suhasdissa.foode

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import app.suhasdissa.foode.backend.database.ENumberDatabase
import app.suhasdissa.foode.utils.UpdateUtil

class FoodeApplication : Application() {
    private val database by lazy { ENumberDatabase.getDatabase(this) }
    private val clipboard by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(database, clipboard)
        UpdateUtil.getCurrentVersion(this.applicationContext)
    }
}