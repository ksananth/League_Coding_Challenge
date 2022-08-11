package life.league.challenge.kotlin.feature.login

import app.cash.turbine.test
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Account
import life.league.challenge.kotlin.repository.LoginRepository
import java.lang.Exception

internal class LoginViewModelTest : BehaviorSpec({

    given("A ${LoginViewModel::class.java.simpleName}") {
        val loginRepository = mockk<LoginRepository>()

        `when`("screen loaded") {

            and("service fails") {
                coEvery { loginRepository.login(any(), any()) } returns ApiResponse.ApiError(Exception("an error"))

                val viewModel = LoginViewModel(loginRepository)

                then("update uiState to error") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe LoginViewModel.UIState.Error
                    }
                }
            }

            and("no internet") {
                coEvery { loginRepository.login(any(), any()) } returns ApiResponse.NoInternetError(Exception("an error"))

                val viewModel = LoginViewModel(loginRepository)

                then("update uiState to no internet") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe LoginViewModel.UIState.NoInternet
                    }
                }
            }

            and("success") {
                val apiKey = "api key"
                coEvery { loginRepository.login(any(), any()) } returns ApiResponse.Success(Account(
                    apiKey
                ))

                val viewModel = LoginViewModel(loginRepository)

                then("navigate to post screen with api key") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe LoginViewModel.UIState.NavigateToPosts(apiKey)
                    }
                }
            }
        }
    }
})