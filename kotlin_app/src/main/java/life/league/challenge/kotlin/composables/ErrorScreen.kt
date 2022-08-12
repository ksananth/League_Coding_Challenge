package life.league.challenge.kotlin.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun ErrorScreen(type: ErrorType) {
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
            Text(text = type.message)
        }
    }
}