package life.league.challenge.kotlin.repository

import com.google.gson.JsonObject
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.parser.PostParser
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class PostsRepositoryImplTest : ShouldSpec({
    val apiKey = "1234"
    val authorization = "an auth"
    val api = mockk<Api>()
    val parser = mockk<PostParser>()

    should("call api with api key when posts service called") {
        coEvery { api.posts(authorization) } returns JsonObject()
        val repository = PostsRepositoryImpl(api, parser)

        repository.getPosts(apiKey)

        coVerify { api.posts(authorization) }
    }


    should("return api error when posts service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { api.posts(authorization) } throws httpException
        val repository = PostsRepositoryImpl(api, parser)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.ApiError(httpException)
    }

    should("return no network error when no internet") {
        val ioException = IOException()
        coEvery { api.posts(authorization) } throws ioException
        val repository = PostsRepositoryImpl(api, parser)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.NoInternetError(ioException)
    }

    should("return list of post when posts service success") {
        val postList = listOf(Post(1, 23, "title", "Description"))
        coEvery { api.posts(authorization) } returns JsonObject()
        coEvery { parser.parse(any()) } returns postList
        val repository = PostsRepositoryImpl(api, parser)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.Success(postList)
    }
})