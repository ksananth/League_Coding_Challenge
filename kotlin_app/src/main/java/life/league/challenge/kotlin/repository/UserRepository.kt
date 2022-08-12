package life.league.challenge.kotlin.repository

import com.google.gson.JsonElement
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse

interface UserRepository {
    suspend fun getUsers(apiKey: String): ApiResponse<JsonElement>
}

class UserRepositoryImpl(private val api: Api) : UserRepository {

    override suspend fun getUsers(apiKey: String): ApiResponse<JsonElement> {
        val result = api.users(apiKey)
        return ApiResponse.Success(data = result)
    }
}