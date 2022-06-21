package com.kok1337.mobiledev.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.databinding.ActivityMainBinding
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortType
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModel.Factory
    private lateinit var mainViewModel: MainViewModel

    private var _prevBackStackEntry: NavBackStackEntry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]


        mainViewModel.currentTbDirectionLD.observe(this) { navigateToToolbarFragment(it) }
        mainViewModel.federalDistrictLD.observe(this) { list ->
            list.forEach{ Log.e("LOL", it.toString()) }
        }


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            controller.backQueue.forEach { Log.e("BackStack", it.destination.displayName) }
        }


        initToolbarActions()
    }

    private fun initToolbarActions() {
        binding.toolbarCamera.setOnClickListener { mainViewModel.onOpenCamera() }
        binding.toolbarMap.setOnClickListener { mainViewModel.onOpenMap() }
        binding.toolbarSettings.setOnClickListener { mainViewModel.onOpenSettings() }
        binding.toolbarWorkTypes.setOnClickListener { mainViewModel.onOpenWorkTypes() }

        binding.toolbarSave.setOnClickListener { mainViewModel.loadFederalDistricts() }
        binding.toolbarEdit.setOnClickListener { binding.toolbarEdit.toggle() }
    }

    private fun navigateToToolbarFragment(direction: NavDirections) {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        _prevBackStackEntry = if (_prevBackStackEntry == null)
            navController.currentBackStackEntry
        else {
            navController.navigateUp()
            navController.currentBackStackEntry
        }

        val navOptions = NavOptions.Builder()
            .setPopUpTo(_prevBackStackEntry!!.destination.id, inclusive = false, saveState = false)
            .build()

        // navController.navigate(direction, navOptions)
        navController.navigate(direction)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
