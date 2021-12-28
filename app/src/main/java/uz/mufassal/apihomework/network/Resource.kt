package uz.mufassal.apihomework.network

import java.lang.Exception
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter

sealed class Resource<out T : Any> {

    object Loading: Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data class GenericError(val errorResponse : ErrorResponse) : Resource<Nothing>()

}
data class ErrorResponse(
    val message : String = "Error"
)