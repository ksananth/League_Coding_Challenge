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

internal class PostsRepositoryImplTest : ShouldSpec({
    val apiKey = "1234"
    val authorization = "an auth"
    val api = mockk<Api>()
    val authorizationHelper = mockk<AuthorizationHelper>()

    should("call api with api key when posts service called") {
        coEvery { authorizationHelper.createAuth(apiKey) } returns authorization
        coEvery { api.posts(authorization) } returns Unit
        val repository = PostsRepositoryImpl(api, authorizationHelper)

        repository.getPosts(apiKey)

        coVerify { api.posts(authorization) }
    }


    should("return api error when posts service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { authorizationHelper.createAuth(apiKey) } returns authorization
        coEvery { api.posts(authorization) } throws httpException
        val repository = PostsRepositoryImpl(api, authorizationHelper)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.ApiError(httpException)
    }
})