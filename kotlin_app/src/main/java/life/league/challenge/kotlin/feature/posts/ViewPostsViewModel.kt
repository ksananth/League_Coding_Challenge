package life.league.challenge.kotlin.feature.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.feature.login.LoginViewModel
import life.league.challenge.kotlin.repository.PostsRepository

internal class ViewPostsViewModel(private val postsRepository: PostsRepository, private val apiKey: String) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState> by lazy {
        MutableStateFlow<UIState>(UIState.Loading).apply {
            viewModelScope.launch {
                when (postsRepository.getPosts(apiKey)) {
                    is ApiResponse.ApiError -> emit(UIState.Error)
                    else -> {}
                }
            }
        }
    }
    val uiState: StateFlow<UIState> get() = _uiState

    internal sealed class UIState{
        object Loading: UIState()
        object Error: UIState()
    }
}