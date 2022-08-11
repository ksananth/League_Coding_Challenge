package life.league.challenge.kotlin.repository

import android.util.Base64
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Account
import retrofit2.HttpException
import java.io.IOException

interface LoginRepository {
    suspend fun login(username: String, password: String): ApiResponse<Account>
}

class LoginRepositoryImpl(
    private val api: Api,
    private val authorizationHelper: AuthorizationHelper
) : LoginRepository {

    override suspend fun login(username: String, password: String): ApiResponse<Account> {
        val authorization = authorizationHelper.create(username, password)
        val result = api.login(authorization)
        return ApiResponse.Success(data = result)
    }
}

class AuthorizationHelper {
    fun create(username: String, password: String): String {
        return "Basic " + Base64.encodeToString(
            "$username:$password".toByteArray(),
            Base64.NO_WRAP
        )
    }
}