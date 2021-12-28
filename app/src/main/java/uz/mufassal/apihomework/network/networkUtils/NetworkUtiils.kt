package uz.mufassal.apihomework.network.networkUtils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import uz.mufassal.apihomework.network.ErrorResponse
import uz.mufassal.apihomework.network.Resource
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(
    errorConverter: Converter<ResponseBody, ErrorResponse> ,
    apiCall: suspend() ->Response<T>
) : Resource<T> {

    try {
        val response = apiCall()

        return if (response.isSuccessful && response.body() != null) {
            Resource.Success<T>(response.body() as T)
        } else {
            errorConverter.convert(response.errorBody()).let { Resource.GenericError(it!!) }
        }
    } catch (throwable: Throwable) {
        return when (throwable) {
            is ConnectException,
            is NoConnectivityException
            -> {
                Resource.Error(NoConnectivityException())
            }
            is HttpException -> {
                val errorResponse: ErrorResponse = throwable.response()?.body() as ErrorResponse
                Resource.GenericError(errorResponse)
            }
            else -> {
                Resource.Error(Exception(throwable.message))
            }
        }

    }
}
    class NoConnectivityException : IOException() {
        override val message: String?
            get() = "Serverda ulanishda xatolik !"
    }