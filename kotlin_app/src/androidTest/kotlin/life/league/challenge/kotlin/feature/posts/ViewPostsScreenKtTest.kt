package life.league.challenge.kotlin.feature.posts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
}