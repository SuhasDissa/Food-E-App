package app.suhasdissa.foode

import android.content.ClipboardManager
import app.suhasdissa.foode.backend.database.ENumberDatabase
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.LocalAdditivesRepository

interface AppContainer {
    val additivesRepository: AdditivesRepository
    val clipboardManager: ClipboardManager
}

class DefaultAppContainer(database: ENumberDatabase, clipboardManager: ClipboardManager) :
    AppContainer {
    override val additivesRepository: AdditivesRepository by lazy {
        LocalAdditivesRepository(
            database.additivesDao()
        )
    }
    override val clipboardManager: ClipboardManager by lazy {
        clipboardManager
    }
}