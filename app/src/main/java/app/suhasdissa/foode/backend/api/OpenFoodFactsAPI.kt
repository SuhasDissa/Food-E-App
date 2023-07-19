package app.suhasdissa.foode.backend.api

import app.suhasdissa.foode.backend.models.OpenFoodFactsResponse
import app.suhasdissa.foode.backend.models.OpenFoodFactsSearchResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .baseUrl("https://world.openfoodfacts.org/")
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

interface ApiService {
    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
    )
    @GET("api/v2/product/{barcode}")
    suspend fun getProductData(
        @Path("barcode") barcode: String
    ): OpenFoodFactsResponse

    @Headers(
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
    )
    @GET(
        "cgi/search.pl?search_simple=1&action=process&fields=code,product_name,brands,image_thumb_url&json=1"
    )
    suspend fun searchProduct(
        @Query("search_terms", encoded = true) q: String
    ): OpenFoodFactsSearchResponse
}

object OpenFoodFactsAPI {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
