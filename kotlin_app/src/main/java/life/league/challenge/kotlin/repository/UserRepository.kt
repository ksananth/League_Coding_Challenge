package life.league.challenge.kotlin.repository

import com.google.gson.JsonElement
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import retrofit2.HttpException

interface UserRepository {
    suspend fun getUsers(apiKey: String): ApiResponse<JsonElement>
}

class UserRepositoryImpl(private val api: Api) : UserRepository {

    override suspend fun getUsers(apiKey: String): ApiResponse<JsonElement> {
        return try {
            val result = api.users(apiKey)
            ApiResponse.Success(data = result)
        } catch (e: HttpException) {
            ApiResponse.ApiError(exception = e)
        }
    }
}