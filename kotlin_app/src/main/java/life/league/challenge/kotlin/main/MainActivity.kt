package life.league.challenge.kotlin.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import life.league.challenge.kotlin.R

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.mainToolBar)

        val mTitle: TextView = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, arguments ->
            mTitle.text = destination.label
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.loginFragment,
            R.id.viewPostsFragment,
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}
