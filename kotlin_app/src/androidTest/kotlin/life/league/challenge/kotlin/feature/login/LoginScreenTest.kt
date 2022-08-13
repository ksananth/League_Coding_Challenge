package life.league.challenge.kotlin.feature.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.mockk.mockk
import life.league.challenge.kotlin.composables.ErrorType
import org.junit.Rule
import org.junit.Test


class LoginScreenTest {

    @Rule
    @JvmField
    var composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginViewModel.UIState.Loading,
                navigate = FakeInteraction(), {}
            )
        }

        composeTestRule.onNodeWithText("Please wait logging in...").assertIsDisplayed()
    }

    @Test
    fun errorState() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginViewModel.UIState.Error,
                navigate = FakeInteraction()
            ){}
        }

        composeTestRule.onNodeWithText(ErrorType.TECHNICAL_ERROR.message).assertIsDisplayed()
    }

    @Test
    fun noInternetState() {
        composeTestRule.setContent {
            LoginScreen(
                state = LoginViewModel.UIState.NoInternet,
                navigate = FakeInteraction()
            ){}
        }

        composeTestRule.onNodeWithText(ErrorType.NO_INTERNET.message).assertIsDisplayed()
    }

    class FakeInteraction : Navigate {
        override fun navigateToPosts(apiKey: String) {

        }

    }
}