package life.league.challenge.kotlin.repository

import com.google.gson.JsonObject
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class UserRepositoryImplTest: ShouldSpec({

    val api = mockk<Api>()
    val apiKey = "a key"
    should("return users when user service success") {
        val response = JsonObject()
        coEvery { api.users(apiKey) } returns response
        val repository = UserRepositoryImpl(api)

        val result = repository.getUsers(apiKey)

        result shouldBe ApiResponse.Success(response)
    }

    should("return api error when user service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { api.users(apiKey) } throws httpException
        val repository = UserRepositoryImpl(api)

        val result = repository.getUsers(apiKey)

        result shouldBe ApiResponse.ApiError(httpException)
    }

    should("return no network error when no internet") {
        val ioException = IOException()
        coEvery { api.users(apiKey) } throws ioException
        val repository = UserRepositoryImpl(api)

        val result = repository.getUsers(apiKey)

        result shouldBe ApiResponse.NoInternetError(ioException)
    }
})