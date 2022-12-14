package life.league.challenge.kotlin.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.feature.posts.ViewPostsViewModel
import life.league.challenge.kotlin.repository.APIInvalidException
import life.league.challenge.kotlin.repository.LoginRepository

private const val USERNAME = "hello"
private const val PASSWORD = "world"

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState> by lazy {
        MutableStateFlow<UIState>(UIState.Loading).apply {
            viewModelScope.launch {
                val state = getApiKey()
                emit(state)
            }
        }
    }

    val uiState: StateFlow<UIState> get() = _uiState

    fun retry() {
        viewModelScope.launch {
            _uiState.emit(UIState.Loading)
            val state = getApiKey()
            _uiState.emit(state)
        }
    }

    private suspend fun getApiKey(): UIState {
        val state = when (val result = loginRepository.login(USERNAME, PASSWORD)) {
            is ApiResponse.ApiError -> if (result.exception is APIInvalidException) UIState.ApiInvalid else UIState.Error
            is ApiResponse.NoInternetError -> UIState.NoInternet
            is ApiResponse.Success -> if (result.data.apiKey.isNullOrEmpty()) {
                UIState.Error
            } else {
                UIState.NavigateToPosts(result.data.apiKey)
            }
        }
        return state
    }

    sealed class UIState {
        object Loading : UIState()
        object Error : UIState()
        object NoInternet : UIState()
        object ApiInvalid : UIState()
        data class NavigateToPosts(val apiKey: String) : UIState()
    }
}

