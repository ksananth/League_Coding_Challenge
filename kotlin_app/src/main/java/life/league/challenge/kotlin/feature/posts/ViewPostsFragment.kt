package life.league.challenge.kotlin.feature.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kraftanapp.myapplication.ui.theme.MyApplicationTheme
import life.league.challenge.kotlin.feature.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewPostsFragment : Fragment() {

    private val viewModel: ViewPostsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val state by remember { viewModel.uiState }.collectAsState()

                MyApplicationTheme {
                    ViewPostsScreen(state)
                }
            }
        }
    }
}