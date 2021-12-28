package uz.mufassal.apihomework.network

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}