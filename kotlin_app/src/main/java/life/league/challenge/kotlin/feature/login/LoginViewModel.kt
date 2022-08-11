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
                when (val result = loginRepository.login(USERNAME, PASSWORD)) {
                    is ApiResponse.ApiError -> emit(UIState.Error)
                    is ApiResponse.NoInternetError -> emit(UIState.NoInternet)
                    is ApiResponse.Success -> if (result.data.apiKey.isNullOrEmpty()) {
                        emit(UIState.Error)
                    } else {
                        emit(UIState.NavigateToPosts(result.data.apiKey))
                    }
                }
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

