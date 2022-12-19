package app.suhasdissa.foode

import app.suhasdissa.foode.backend.database.ENumberDatabase
import app.suhasdissa.foode.backend.repositories.AdditivesRepository
import app.suhasdissa.foode.backend.repositories.LocalAdditivesRepository

interface AppContainer {
    val additivesRepository: AdditivesRepository
}

class DefaultAppContainer(database: ENumberDatabase) : AppContainer {
    override val additivesRepository: AdditivesRepository by lazy {
        LocalAdditivesRepository(
            database.additivesDao()
        )
    }
}