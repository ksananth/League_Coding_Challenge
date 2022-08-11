package life.league.challenge.kotlin.feature.posts

import app.cash.turbine.test
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.PostsRepository

internal class ViewPostsViewModelTest:BehaviorSpec({

    given("A ${ ViewPostsViewModel::class.java.simpleName}") {
        val apiKey = "an api key"
        val postsRepository = mockk<PostsRepository>()

        `when`("screen loaded") {

            and("service fails") {
                coEvery { postsRepository.getPosts(any()) } returns ApiResponse.ApiError(Exception("an error"))

                val viewModel = ViewPostsViewModel(postsRepository, apiKey)

                then("update uiState to error") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe ViewPostsViewModel.UIState.Error
                    }
                }
            }

            and("no internet") {
                coEvery { postsRepository.getPosts(any()) } returns ApiResponse.NoInternetError(
                    java.lang.Exception("an error")
                )

                val viewModel = ViewPostsViewModel(postsRepository, apiKey)

                then("update uiState to no internet") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe ViewPostsViewModel.UIState.NoInternet
                    }
                }
            }

            and("success") {
                val data = listOf(Post(1, 234, "a titile", " a body"))
                coEvery { postsRepository.getPosts(any()) } returns ApiResponse.Success(data)

                val viewModel = ViewPostsViewModel(postsRepository, apiKey)

                then("show data") {
                    viewModel.uiState.test {
                        awaitItem() shouldBe ViewPostsViewModel.UIState.Data(data)
                    }
                }
            }

        }
    }
})