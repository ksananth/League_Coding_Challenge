package life.league.challenge.kotlin.feature.login

import androidx.compose.runtime.Composable
import life.league.challenge.kotlin.composables.Loading

@Composable
fun LoginScreen(state: LoginViewModel.UIState) {
    when (state) {
        is LoginViewModel.UIState.Error -> TODO()
        is LoginViewModel.UIState.Loading -> Loading()
        is LoginViewModel.UIState.NavigateToPosts -> TODO()
        is LoginViewModel.UIState.NoInternet -> TODO()
    }
}