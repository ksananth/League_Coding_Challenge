package life.league.challenge.kotlin.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.api.login

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.mainToolBar)
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()

        // example api call to login, feel free to delete this and implement the call to login
        // somewhere else differently depending on your chosen architecture
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val account = Service.api.login("hello", "world")
                Log.v(TAG, account.apiKey ?: "")
            } catch (t : Throwable) {
                Log.e(TAG, t.message, t)
            }
        }
    }

}
