package life.league.challenge.kotlin.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState> by lazy {
        MutableStateFlow<UIState>(UIState.Loading).apply {
            viewModelScope.launch {
                when (loginRepository.login("hello", "world")) {
                    is ApiResponse.ApiError -> emit(UIState.Error)
                    is ApiResponse.NoInternetError -> TODO()
                    is ApiResponse.Success -> TODO()
                }
            }
        }
    }
    val uiState: StateFlow<UIState> get() = _uiState


    sealed class UIState {
        object Loading : UIState()
        object Error : UIState()
    }
}

