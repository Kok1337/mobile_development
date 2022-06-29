package com.kok1337.mobiledev.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.activityViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.databinding.ActivityMainBinding
import com.kok1337.mobiledev.presentation.util.showToast
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private lateinit var viewModel: MainViewModel

    private var _prevBackStackEntry: NavBackStackEntry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        viewModel.currentTbDirectionLD.observe(this) { navigateToToolbarFragment(it) }
        viewModel.federalDistrictLD.observe(this) { list ->
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
        binding.toolbarCamera.setOnClickListener { viewModel.onOpenCamera() }
        binding.toolbarMap.setOnClickListener { viewModel.onOpenMap() }
        binding.toolbarSettings.setOnClickListener { viewModel.onOpenSettings() }
        binding.toolbarWorkTypes.setOnClickListener { viewModel.onOpenWorkTypes() }

        binding.toolbarEdit.setOnClickListener { changeEnabled() }
    }

    private fun changeEnabled() {
        val toolbarEdit = binding.toolbarEdit
        toolbarEdit.toggle()
        val isChecked = toolbarEdit.isChecked
        viewModel.setEditEnabled(isChecked)
        showToast(if (isChecked) "Редактирование включено" else "Редактирование отключено")
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

}
