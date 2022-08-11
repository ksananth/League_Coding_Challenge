package life.league.challenge.kotlin.feature.posts

import androidx.compose.runtime.Composable
import life.league.challenge.kotlin.composables.ErrorScreen
import life.league.challenge.kotlin.composables.Loading
import life.league.challenge.kotlin.feature.login.LoginViewModel

@Composable
internal fun ViewPostsScreen(state: ViewPostsViewModel.UIState) {
    when(state){
        is ViewPostsViewModel.UIState.Data -> TODO()
        ViewPostsViewModel.UIState.Error -> ErrorScreen()
        ViewPostsViewModel.UIState.Loading -> Loading("Fetching posts...")
        ViewPostsViewModel.UIState.NoInternet -> TODO()
    }
}