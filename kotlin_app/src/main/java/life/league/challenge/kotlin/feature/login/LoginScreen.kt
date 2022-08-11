package life.league.challenge.kotlin.feature.login

import androidx.compose.runtime.Composable
import life.league.challenge.kotlin.composables.ErrorScreen
import life.league.challenge.kotlin.composables.Loading
import life.league.challenge.kotlin.composables.NoInternetScreen

@Composable
fun LoginScreen(state: LoginViewModel.UIState) {
    when (state) {
        is LoginViewModel.UIState.Error -> ErrorScreen()
        is LoginViewModel.UIState.Loading -> Loading("Please wait logging in...")
        is LoginViewModel.UIState.NavigateToPosts -> TODO()
        is LoginViewModel.UIState.NoInternet -> NoInternetScreen()
    }
}