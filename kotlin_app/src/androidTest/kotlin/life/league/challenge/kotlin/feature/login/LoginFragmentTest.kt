package life.league.challenge.kotlin.feature.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.kraftanapp.myapplication.ui.theme.MyApplicationTheme
import org.junit.Rule
import org.junit.Test


internal class LoginFragmentTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun loadingState() {
        composeTestRule.setContent {
            MyApplicationTheme {
                LoginScreen(state = LoginViewModel.UIState.Loading)
            }
        }

        composeTestRule.onNodeWithText("Please wait logging in...").assertIsDisplayed()
    }
}