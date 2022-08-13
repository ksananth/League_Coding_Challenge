package life.league.challenge.kotlin.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import life.league.challenge.kotlin.R

enum class ErrorType(val message: String, val image: Int) {
    API_INVALID("Api key invalid.", R.drawable.ill_otp_authentication_security),
    NO_INTERNET("Please check your internet connection.", R.drawable.ill_no_internet),
    TECHNICAL_ERROR(
        "Something went wrong. Please try again later",
        R.drawable.ill_internal_server_error
    )
}

@Composable
fun ErrorScreen(type: ErrorType, listener: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = type.image),
                contentDescription = "illustration",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        Snackbar(listener, modifier = Modifier.align(Alignment.BottomCenter), type.message)
    }
}


@Composable
fun Snackbar(listener: () -> Unit, modifier: Modifier, message: String) {
    Column(modifier = modifier) {
        val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(true) }
        if (snackbarVisibleState) {
            androidx.compose.material.Snackbar(
                action = {
                    Button(onClick = {
                        listener.invoke()
                        setSnackBarState(!snackbarVisibleState)
                    }) {
                        Text("Retry!")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = message) }
        }
    }
}
