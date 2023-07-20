package app.suhasdissa.foode.backend.api

import app.suhasdissa.foode.backend.models.lingva.LVTranslationResponse
import app.suhasdissa.foode.backend.models.lingva.LvLanguage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private val lingvaRetrofit = Retrofit.Builder()
    .baseUrl("https://lingva.ml/")
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

interface LingvaTranslateApiService {
    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
    )
    @GET("api/v1/languages")
    suspend fun getLanguages(): LvLanguage

    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
    )
    @GET("api/v1/en/{target}/{query}")
    suspend fun translate(
        @Path("target") target: String,
        @Path("query") query: String
    ): LVTranslationResponse
}

object LingvaTranslateAPI {
    val retrofitService: LingvaTranslateApiService by lazy {
        lingvaRetrofit.create(LingvaTranslateApiService::class.java)
    }
}
