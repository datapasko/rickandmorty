package es.smarting.rickmortyapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        withContext(Dispatchers.IO) {
            Result.success(call())
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}