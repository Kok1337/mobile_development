package com.kok1337.mobiledev.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.ActivityMainBinding
import com.kok1337.mobiledev.domain.model.FederalDistrict
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModel<MainViewModel>()

    private var _prevBackStackEntry: NavBackStackEntry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fd1: FederalDistrict = FederalDistrict(1, "s")
        val fd2: FederalDistrict = FederalDistrict(2, "s")
        fd1.id
        fd1.value
        Log.e("TAS", (fd1 == fd2).toString())

        mainViewModel.currentTbDirectionLD.observe(this) { navigateToToolbarFragment(it) }
        mainViewModel.federalDistrictLD.observe(this) { list -> list.forEach( { Log.e("LOL", it.toString())})}

        initToolbarActions()
    }

    private fun initToolbarActions() {
        binding.toolbarCamera.setOnClickListener{ mainViewModel.onOpenCamera() }
        binding.toolbarMap.setOnClickListener{ mainViewModel.onOpenMap() }
        binding.toolbarSettings.setOnClickListener{ mainViewModel.onOpenSettings() }
        binding.toolbarWorkTypes.setOnClickListener{ mainViewModel.onOpenWorkTypes() }

        binding.toolbarSave.setOnClickListener { mainViewModel.loadFederalDistricts() }
        binding.toolbarEdit.setOnClickListener{binding.toolbarEdit.toggle()}
    }

    private fun navigateToToolbarFragment(direction: NavDirections) {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        _prevBackStackEntry = if (_prevBackStackEntry == null)
            navController.currentBackStackEntry
        else {
            navController.navigateUp()
            navController.currentBackStackEntry
        }

        navController.backQueue.forEach{Log.e("BackStack", it.destination.displayName)}

        val navOptions = NavOptions.Builder()
            .setPopUpTo(_prevBackStackEntry!!.destination.id, inclusive = false, saveState = false)
            .build()
        navController.navigate(direction, navOptions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}