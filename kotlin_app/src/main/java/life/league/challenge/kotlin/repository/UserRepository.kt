package life.league.challenge.kotlin.repository

import com.google.gson.JsonElement
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.User
import life.league.challenge.kotlin.repository.parser.UserParser
import retrofit2.HttpException
import java.io.IOException

interface UserRepository {
    suspend fun getUsers(apiKey: String): ApiResponse<List<User>>
}

class UserRepositoryImpl(private val api: Api,private val parser: UserParser) : UserRepository {

    override suspend fun getUsers(apiKey: String): ApiResponse<List<User>> {
        return try {
            val result = api.users(apiKey)
            val users = parser.parse(result)
            ApiResponse.Success(data = users)
        } catch (e: HttpException) {
            ApiResponse.ApiError(exception = e)
        } catch (e: IOException) {
            ApiResponse.NoInternetError(exception = e)
        }
    }
}