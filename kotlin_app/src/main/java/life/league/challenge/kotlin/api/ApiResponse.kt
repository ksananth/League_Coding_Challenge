package life.league.challenge.kotlin.api

sealed class ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class ApiError<T>(val exception: Exception) : ApiResponse<T>()
    data class NoInternetError<T>(val exception: Exception) : ApiResponse<T>()
}