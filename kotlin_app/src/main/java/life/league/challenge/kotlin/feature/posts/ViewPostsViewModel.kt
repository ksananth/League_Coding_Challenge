package life.league.challenge.kotlin.feature.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.feature.login.LoginViewModel
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.APIInvalidException
import life.league.challenge.kotlin.repository.PostsRepository

internal class ViewPostsViewModel(
    private val postsRepository: PostsRepository,
    private val apiKey: String
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState> by lazy {
        MutableStateFlow<UIState>(UIState.Loading).apply {
            viewModelScope.launch {
                when (val result = postsRepository.getPosts(apiKey)) {
                    is ApiResponse.NoInternetError -> emit(UIState.NoInternet)
                    is ApiResponse.Success -> emit(UIState.Data(result.data))
                    is ApiResponse.ApiError -> if (result.exception is APIInvalidException) {
                        emit(UIState.ApiInvalid)
                    } else {
                        emit(UIState.Error)
                    }
                }
            }
        }
    }
    val uiState: StateFlow<UIState> get() = _uiState

    internal sealed class UIState {
        data class Data(val data: List<Post>) : UIState()
        object Loading : UIState()
        object Error : UIState()
        object ApiInvalid : UIState()
        object NoInternet : UIState()
    }
}