package life.league.challenge.kotlin

import androidx.compose.runtime.ExperimentalComposeApi
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.time.Duration

class KotestProjectConfig : AbstractProjectConfig() {
    override val timeout: Duration?
        get() = super.timeout
    override val isolationMode: IsolationMode
        get() = IsolationMode.InstancePerLeaf

    override fun extensions(): List<Extension> =
        listOf(CoroutinesTestListener)
}

@OptIn(ExperimentalComposeApi::class)
object CoroutinesTestListener : TestListener {
    private val testDispatcher = TestCoroutineDispatcher()

    override suspend fun beforeSpec(spec: Spec) {
        Dispatchers.setMain(testDispatcher)
    }

    override suspend fun afterSpec(spec: Spec) {
        Dispatchers.resetMain()
    }
}

