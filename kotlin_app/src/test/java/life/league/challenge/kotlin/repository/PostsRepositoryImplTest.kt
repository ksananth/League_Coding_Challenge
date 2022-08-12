package life.league.challenge.kotlin.repository

import com.google.gson.JsonObject
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.domain.User
import life.league.challenge.kotlin.domain.UserPost
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.parser.PostParser
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class PostsRepositoryImplTest : ShouldSpec({
    val apiKey = "1234"
    val user = User(1, "an avatar", "ananth")
    val users = listOf(user)
    val api = mockk<Api>()
    val parser = mockk<PostParser>()
    val userRepository = mockk<UserRepository>() {
        coEvery { getUsers(apiKey) } returns ApiResponse.Success(users)
    }

    should("call api with api key when posts service called") {
        val response = JsonObject()
        val posts = listOf(Post(1, 2, "title", "a body"))
        coEvery { api.posts(apiKey) } returns response
        coEvery { parser.parse(response) } returns posts
        val repository = PostsRepositoryImpl(api, parser, userRepository)

        repository.getPosts(apiKey)

        coVerify { api.posts(apiKey) }
    }


    should("return api error when posts service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { api.posts(apiKey) } throws httpException
        val repository = PostsRepositoryImpl(api, parser, userRepository)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.ApiError(httpException)
    }

    should("return no network error when no internet") {
        val ioException = IOException()
        coEvery { api.posts(apiKey) } throws ioException
        val repository = PostsRepositoryImpl(api, parser, userRepository)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.NoInternetError(ioException)
    }

    should("return list of post when posts service success") {
        val postList = listOf(Post(1, 23, "title", "Description"))
        coEvery { api.posts(apiKey) } returns JsonObject()
        coEvery { parser.parse(any()) } returns postList
        val repository = PostsRepositoryImpl(api, parser, userRepository)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.Success(listOf(UserPost("an avatar","ananth","title", "Description")))
    }

    should("return api error when users service fails") {
        val httpException = HttpException(
            Response.error<ResponseBody>(
                500, "error".toResponseBody()
            )
        )
        coEvery { userRepository.getUsers(apiKey) } returns ApiResponse.ApiError(httpException)
        coEvery { api.posts(apiKey) } returns JsonObject()
        val repository = PostsRepositoryImpl(api, parser, userRepository)

        val result = repository.getPosts(apiKey)

        result shouldBe ApiResponse.ApiError(httpException)
    }
})