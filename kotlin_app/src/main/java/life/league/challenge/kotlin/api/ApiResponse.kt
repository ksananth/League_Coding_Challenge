package life.league.challenge.kotlin.api

sealed class ApiResponse<T>(data: T? = null, exception: Exception? = null) {
    data class Success<T>(val data: T) : ApiResponse<T>(data, null)
    data class ApiError<T>(val exception: Exception) : ApiResponse<T>(null, exception)
    data class NoInternetError<T>(val exception: Exception) : ApiResponse<T>(null, exception)
}