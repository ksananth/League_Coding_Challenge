package life.league.challenge.kotlin.feature.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import life.league.challenge.kotlin.composables.ErrorScreen
import life.league.challenge.kotlin.composables.Loading
import life.league.challenge.kotlin.composables.NoInternetScreen
import life.league.challenge.kotlin.domain.UserPost
import life.league.challenge.kotlin.R

const val TEST_TAG_IMAGE = "IMAGE"

@Composable
internal fun ViewPostsScreen(state: ViewPostsViewModel.UIState) {
    when (state) {
        is ViewPostsViewModel.UIState.Data -> ShowPosts(state.data)
        ViewPostsViewModel.UIState.Error -> ErrorScreen("Something went wrong")
        ViewPostsViewModel.UIState.Loading -> Loading("Fetching posts...")
        ViewPostsViewModel.UIState.NoInternet -> NoInternetScreen()
        ViewPostsViewModel.UIState.ApiInvalid -> ErrorScreen("Api invalid")
    }
}

@Composable
fun ShowPosts(data: List<UserPost>) {
    LazyColumn {
        items(data.size) { index ->
            PostCard(data[index])
        }
    }
}

@Composable
fun PostCard(msg: UserPost) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = msg.avatar, error = painterResource(R.drawable.ic__avatar), placeholder = painterResource(R.drawable.ic_loading)),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .testTag("IMAGE")
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.description)
        }
    }
}