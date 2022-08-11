package life.league.challenge.kotlin.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.repository.LoginRepository

private const val USERNAME = "hello"
private const val PASSWORD = "world"

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState> by lazy {
        MutableStateFlow<UIState>(UIState.Loading).apply {
            viewModelScope.launch {
                val state = when (val result = loginRepository.login(USERNAME, PASSWORD)) {
                    is ApiResponse.ApiError -> UIState.Error
                    is ApiResponse.NoInternetError -> UIState.NoInternet
                    is ApiResponse.Success -> if (result.data.apiKey.isNullOrEmpty()) {
                        UIState.Error
                    } else {
                        UIState.NavigateToPosts(result.data.apiKey)
                    }
                }
                emit(state)
            }
        }
    }
    val uiState: StateFlow<UIState> get() = _uiState


    sealed class UIState {
        object Loading : UIState()
        object Error : UIState()
        object NoInternet : UIState()
        data class NavigateToPosts(val apiKey: String) : UIState()
    }
}

