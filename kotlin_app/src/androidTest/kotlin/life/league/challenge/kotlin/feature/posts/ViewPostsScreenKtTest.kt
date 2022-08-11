package life.league.challenge.kotlin.feature.posts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import life.league.challenge.kotlin.model.Post
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

    @Test
    fun showPost() {
        val title = "a title"
        val body = "a body"

        composeTestRule.setContent {
            val aPost = Post("1", "id", title, body)
            ViewPostsScreen(state = ViewPostsViewModel.UIState.Data(listOf(aPost)))
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(body).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TEST_TAG_IMAGE).assertIsDisplayed()
    }

}