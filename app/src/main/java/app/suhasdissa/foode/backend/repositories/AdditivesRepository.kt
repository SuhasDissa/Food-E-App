package app.suhasdissa.foode.backend.repositories

import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive

interface AdditivesRepository {
    suspend fun getAdditives(): ArrayList<DetailedAdditive>
    suspend fun getAdditive(id: Int): DetailedAdditive
    suspend fun search(search: String): ArrayList<DetailedAdditive>
}