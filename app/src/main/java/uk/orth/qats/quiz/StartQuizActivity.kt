package uk.orth.qats.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import uk.orth.qats.R
import uk.orth.qats.databinding.ActivityStartQuizBinding

class StartQuizActivity : AppCompatActivity() {
    // TODO let user show QR code if multiplayer button pressed in Activity

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityStartQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_start_quiz)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_start_quiz)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}