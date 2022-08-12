package life.league.challenge.kotlin.repository

import com.google.gson.JsonObject
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import org.junit.jupiter.api.Assertions.*

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
})