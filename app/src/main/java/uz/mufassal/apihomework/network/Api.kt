package uz.mufassal.apihomework.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.mufassal.apihomework.network.data.FruitResponse
import uz.mufassal.apihomework.network.data.WordsResponse

interface Api {
    //    @GET("api/breeds/image/random")
    @GET("api/v2/entries/en/{words}")
    suspend fun getRandomDogs(word: String): Response<WordsResponse>

    @GET("api/v2/entries/en/hello")
    suspend fun getWords(): Response<WordsResponse>

    @GET("api/fruit/{fruit}")
    suspend fun getFruit(@Path("fruit") fruit: String): Response<FruitResponse>
}