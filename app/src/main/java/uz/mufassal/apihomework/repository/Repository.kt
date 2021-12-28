package uz.mufassal.apihomework.repository

import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Converter
import uz.mufassal.apihomework.network.Api
import uz.mufassal.apihomework.network.ErrorResponse
import uz.mufassal.apihomework.network.Resource
import uz.mufassal.apihomework.network.networkUtils.safeApiCall

class Repository(private val api: Api, var errorConverter: Converter<ResponseBody, ErrorResponse>) {

    suspend fun getRandom(word: String) = flow {
        emit(Resource.Loading)
        emit(safeApiCall(errorConverter) { api.getRandomDogs(word) })
    }

    suspend fun getWords(word: String) = flow {
        emit(Resource.Loading)
        emit(safeApiCall(errorConverter) { api.getWords() })
    }

    suspend fun getFruits(fruits: String) = flow {
        emit(Resource.Loading)
        emit(safeApiCall(errorConverter) { api.getFruit(fruits) })
    }
}