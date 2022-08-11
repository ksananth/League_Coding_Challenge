package life.league.challenge.kotlin.feature.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import life.league.challenge.kotlin.composables.ErrorScreen
import life.league.challenge.kotlin.composables.Loading
import life.league.challenge.kotlin.composables.NoInternetScreen

@Composable
fun LoginScreen(state: LoginViewModel.UIState, navigate: Navigate) {
    when (state) {
        is LoginViewModel.UIState.Error -> ErrorScreen()
        is LoginViewModel.UIState.Loading -> Loading("Please wait logging in...")
        is LoginViewModel.UIState.NavigateToPosts -> navigate.navigateToPosts(state.apiKey)
        is LoginViewModel.UIState.NoInternet -> NoInternetScreen()
    }
}