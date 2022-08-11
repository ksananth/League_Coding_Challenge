package life.league.challenge.kotlin.repository

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Account

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
})