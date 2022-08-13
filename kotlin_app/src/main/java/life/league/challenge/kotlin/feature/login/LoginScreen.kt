package life.league.challenge.kotlin.feature.login

import androidx.compose.runtime.Composable
import life.league.challenge.kotlin.composables.ErrorScreen
import life.league.challenge.kotlin.composables.ErrorType
import life.league.challenge.kotlin.composables.Loading


@Composable
fun LoginScreen(state: LoginViewModel.UIState, navigate: Navigate, listener: () -> Unit) {
    when (state) {
        is LoginViewModel.UIState.Error -> ErrorScreen(ErrorType.TECHNICAL_ERROR, listener)
        is LoginViewModel.UIState.Loading -> Loading("Please wait logging in...")
        is LoginViewModel.UIState.NavigateToPosts -> navigate.navigateToPosts(state.apiKey)
        is LoginViewModel.UIState.NoInternet -> ErrorScreen(ErrorType.NO_INTERNET, listener)
    }
}
