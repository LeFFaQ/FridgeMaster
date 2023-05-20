package ru.lffq.fmaster.common

import androidx.annotation.Keep

@Keep
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message = message)
    class Loading<T> : Resource<T>()
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}