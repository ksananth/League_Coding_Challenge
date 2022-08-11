package life.league.challenge.kotlin.repository

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.model.Account

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
})