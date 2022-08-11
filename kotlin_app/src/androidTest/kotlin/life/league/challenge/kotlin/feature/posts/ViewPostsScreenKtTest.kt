package life.league.challenge.kotlin.feature.posts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import life.league.challenge.kotlin.feature.login.LoginScreen
import life.league.challenge.kotlin.feature.login.LoginViewModel
import org.junit.Rule
import org.junit.Test


internal class ViewPostsScreenKtTest {

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState() {
        composeTestRule.setContent {
            ViewPostsScreen(state = ViewPostsViewModel.UIState.Loading)
        }

        composeTestRule.onNodeWithText("Fetching posts...").assertIsDisplayed()
    }

    @Test
    fun errorState() {
        composeTestRule.setContent {
            ViewPostsScreen(state = ViewPostsViewModel.UIState.Error)
        }

        composeTestRule.onNodeWithText("Something went wrong").assertIsDisplayed()
    }

    @Test
    fun noInternetState() {
        composeTestRule.setContent {
            ViewPostsScreen(state = ViewPostsViewModel.UIState.NoInternet)
        }

        composeTestRule.onNodeWithText("Please check your internet connections.").assertIsDisplayed()
    }

}