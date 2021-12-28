package uz.mufassal.apihomework.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.mufassal.apihomework.network.Api
import uz.mufassal.apihomework.network.ErrorResponse
import java.util.concurrent.TimeUnit

//const val BASE_URL: String ="https://dog.ceo/"
//const val BASE_URL: String ="https://api.dictionaryapi.dev/"
//const val BASE_URL: String = "https://dictionaryapi.dev/"
const val BASE_URL: String = "https://www.fruityvice.com/"

val netWorkModule = module {

    single<Api> {
        val retrofit = get<Retrofit>()
        retrofit.create(Api::class.java)
    }

    single<Gson> {
        GsonBuilder()
            .setLenient()
            .create()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single<OkHttpClient> {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(3000, TimeUnit.SECONDS)
            .readTimeout(3000, TimeUnit.SECONDS)
            .writeTimeout(3000, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                try {
                    val request = chain.request().newBuilder()
                    request.addHeader("Content-type", "application/json")
                    request.addHeader("X-Request-With", "XMLHttpRequest")
//                    if (token != "")
//                        request.addHeader("Authorization", "Bearer $token")
                    return@addInterceptor chain.proceed(request.build())
                } catch (e: Throwable) {
                    Log.d("exception:", "${e.message}")
                }
                return@addInterceptor chain.proceed(chain.request())
            }
        clientBuilder.build()
    }
    factory<Converter<ResponseBody, ErrorResponse>> {
        get<Retrofit>().responseBodyConverter(
            ErrorResponse::class.java,
            arrayOfNulls<Annotation>(0)
        )
    }

}