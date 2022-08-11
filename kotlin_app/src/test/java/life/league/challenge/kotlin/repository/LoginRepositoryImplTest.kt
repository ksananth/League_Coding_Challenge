package life.league.challenge.kotlin.repository

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Account
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class LoginRepositoryImplTest : ShouldSpec({
    val username = "a name"
    val password = "a password"
    val account = Account("an api key")
    val authorization = "an authorization"

    val api = mockk<Api>()
    val authorizationHelper = mockk<AuthorizationHelper>()

    should("call api with authorization when login service called") {
        coEvery { authorizationHelper.create(username, password) } returns authorization
        coEvery { api.login(authorization) } returns Account("an api key")
        val repository = LoginRepositoryImpl(api, authorizationHelper)

        repository.login(username, password)

        coVerify { api.login(authorization) }
    }

    should("return account when login service success") {
        coEvery { authorizationHelper.create(username, password) } returns authorization
        coEvery { api.login(authorization) } returns account
        val repository = LoginRepositoryImpl(api, authorizationHelper)

        val result = repository.login(username, password)

        result shouldBe ApiResponse.Success(account)
    }

    should("return api error when login service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { authorizationHelper.create(username, password) } returns authorization
        coEvery { api.login(authorization) } throws httpException
        val repository = LoginRepositoryImpl(api, authorizationHelper)

        val result = repository.login(username, password)

        result shouldBe ApiResponse.ApiError(httpException)
    }

    should("return no network error when no internet") {
        val ioException = IOException()
        coEvery { authorizationHelper.create(username, password) } returns authorization
        coEvery { api.login(authorization) } throws ioException
        val repository = LoginRepositoryImpl(api, authorizationHelper)

        val result = repository.login(username, password)

        result shouldBe ApiResponse.NoInternetError(ioException)
    }
})