package cl.maleb.jetpackexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cl.maleb.jetpackexample.databinding.ActivityMainBinding
import cl.maleb.jetpackexample.ui.dashboard.DashboardFragment
import cl.maleb.jetpackexample.ui.home.HomeFragment
import cl.maleb.jetpackexample.ui.home.HomeViewData
import cl.maleb.jetpackexample.ui.notifications.NotificationsDetailFragment
import cl.maleb.jetpackexample.ui.notifications.NotificationsFragment
import cl.maleb.jetpackexample.util.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            testGraph2()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        testGraph2()
    }

    /*private fun setupBottomNavigationBar2(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment, R.id.notificationsFragment, R.id.dashboardFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }*/

    private fun NavController.getBasicGraph(graphId: Int, destId:Int): NavGraph{
        return createGraph(graphId,destId) {
                    fragment<HomeFragment>(R.id.dest_home) {
                        label = "Home"
                        action(R.id.action_to_notifications) {
                            destinationId = R.id.dest_notifications
                        }
                    }
                    fragment<NotificationsFragment>(R.id.dest_notifications) {
                        label = "Notifications"
                        action(R.id.action_to_home) {
                            destinationId = R.id.dest_home
                        }
                        argument(getString(R.string.args_home)){
                            type = NavType.fromArgType(HomeViewData::class.qualifiedName,null)
                            defaultValue = null
                            nullable = true
                        }

                    }
                    fragment<NotificationsDetailFragment>(R.id.dest_notificationsDetails) {
                        label = "Details"
                    }
                    fragment<DashboardFragment>(R.id.dest_dashboard) {
                        label = "Dashboard"
                    }
                }

    }

    private fun testGraph() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navHostFragment.navController.apply {
            graph = getBasicGraph(R.id.graph_id, R.id.dest_home)
        }

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.dest_home, R.id.dest_notifications, R.id.dest_dashboard))
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        navView.setupWithNavController(navHostFragment.navController)
    }

    private fun testGraph2() {
        val bottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navHostFragment.navController.apply {
            graph = getBasicGraph(R.id.graph_id, R.id.dest_home)
            graph = getBasicGraph(R.id.graph_id_2, R.id.dest_dashboard)
            graph = getBasicGraph(R.id.graph_id_3, R.id.dest_notifications)
        }

        val navGraphIds = listOf(
            navHostFragment.navController.graph.id, R.id.graph_id_2, R.id.graph_id_3)

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent
        )
        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)
        }
        currentNavController = controller
    }

    /**
     * Called on first creation and when restoring state.
     */
    /*private fun setupBottomNavigationBar() {

        val bottomNavigationView = binding.navView
        val navGraphIds = listOf(
            R.navigation.main_nav_home,
            R.navigation.main_nav_dashboard,
            R.navigation.main_nav_notification
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this) { navController ->
            setupActionBarWithNavController(navController)
        }
        currentNavController = controller
    }*/

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}