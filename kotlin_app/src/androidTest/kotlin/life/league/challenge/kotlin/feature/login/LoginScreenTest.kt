package life.league.challenge.kotlin.feature.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import io.mockk.mockk
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
                    navigate = mockk()
                )
        }

        composeTestRule.onNodeWithText("Please wait logging in...").assertIsDisplayed()
    }

     @Test
     fun errorState() {
         composeTestRule.setContent {
             LoginScreen(
                 state = LoginViewModel.UIState.Error,
                 navigate = mockk()
             )
         }

         composeTestRule.onNodeWithText("Something went wrong").assertIsDisplayed()
     }

     @Test
     fun noInternetState() {
         composeTestRule.setContent {
             LoginScreen(
                 state = LoginViewModel.UIState.NoInternet,
                 navigate = mockk()
             )
         }

         composeTestRule.onNodeWithText("Please check your internet connections.").assertIsDisplayed()
     }
}