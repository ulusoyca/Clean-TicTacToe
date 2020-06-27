package com.ulusoyapps.tictactoe.domain.entitiy

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out T>

data class Success<out T>(val data: T) : Resource<T>()
data class Failure(val reason: Reason) : Resource<Nothing>()
object Loading : Resource<Nothing>()

data class Reason(val msg: String, val exception: Throwable? = null)
