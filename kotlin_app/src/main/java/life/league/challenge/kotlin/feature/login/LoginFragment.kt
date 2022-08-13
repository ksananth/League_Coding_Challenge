package life.league.challenge.kotlin.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kraftanapp.myapplication.ui.theme.MyApplicationTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), Navigate {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by remember { viewModel.uiState }.collectAsState()
                MyApplicationTheme {
                    LoginScreen(state, this@LoginFragment) {
                        viewModel.retry()
                    }
                }
            }
        }
    }

    override fun navigateToPosts(apiKey: String) {
        val direction = LoginFragmentDirections.actionLoginFragmentToViewPosts(apiKey)
        findNavController().navigate(direction)
    }
}

interface Navigate {
    fun navigateToPosts(apiKey: String)
}