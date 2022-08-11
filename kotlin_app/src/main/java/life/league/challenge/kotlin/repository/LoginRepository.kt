package life.league.challenge.kotlin.repository

import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse

interface LoginRepository {
    suspend fun login(username: String, password: String)
}

class LoginRepositoryImpl(private val api: Api, private val authorizationHelper: AuthorizationHelper) : LoginRepository {

    override suspend fun login(username: String, password: String) {
        val authorization = authorizationHelper.create(username, password)
        api.login(authorization)
    }
}

class AuthorizationHelper {
    fun create(username: String, password: String): String {
        val authorization = "Basic " + android.util.Base64.encodeToString(
            "$username:$password".toByteArray(),
            android.util.Base64.NO_WRAP
        )
        return authorization
    }
}