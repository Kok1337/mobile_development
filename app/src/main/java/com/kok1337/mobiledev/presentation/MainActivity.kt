package com.kok1337.mobiledev.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.databinding.ActivityMainBinding
import com.kok1337.mobiledev.domain.usecase.SaveUserIdUseCase
import com.kok1337.mobiledev.presentation.fragment.toolbar.RootFragment
import com.kok1337.mobiledev.presentation.navigation.AppToolbarNavigator
import com.kok1337.mobiledev.presentation.navigation.Navigator
import com.kok1337.mobiledev.presentation.util.showToast
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), Navigator {

    private val binding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var viewModelFactory: MainViewModel.Factory
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var saveUserIdUseCase: SaveUserIdUseCase

    private val toolbarNavigator = AppToolbarNavigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.saveUserId(saveUserIdUseCase)

        toolbarNavigator.initStartFragment(savedInstanceState, RootFragment())
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)

        Log.e("MainActivity", "onCreate")

        initToolbarActions()
    }

    private fun initToolbarActions() {
        binding.toolbarCamera.setOnClickListener { toolbarNavigator.showCameraScreen() }
        binding.toolbarMap.setOnClickListener { toolbarNavigator.showMapScreen() }
        binding.toolbarSettings.setOnClickListener { toolbarNavigator.showSettingScreen() }
        binding.toolbarWorkTypes.setOnClickListener { toolbarNavigator.showWorkTypesScreen() }
        binding.toolbarSynchronization.setOnClickListener { toolbarNavigator.showSynchronizationScreen() }

        binding.toolbarEdit.setOnClickListener { changeEnabled() }
    }

    private fun resetSelectToolbarItem() {
        with(toolbarNavigator) {
            val name = upperToolbarFragmentName
            binding.toolbarCamera.isSelected = isCameraFragment(name)
            binding.toolbarMap.isSelected = isMapFragment(name)
            binding.toolbarSettings.isSelected = isSettingsFragment(name)
            binding.toolbarWorkTypes.isSelected = isWorkTypesFragment(name)
            binding.toolbarSynchronization.isSelected = isSynchronizationFragment(name)
        }
    }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            resetSelectToolbarItem()
            printBackStack()
        }
    }

    private fun changeEnabled() {
        val toolbarEdit = binding.toolbarEdit
        toolbarEdit.toggle()
        val isChecked = toolbarEdit.isChecked
        viewModel.setEditEnabled(isChecked)
        showToast(if (isChecked) "Редактирование включено" else "Редактирование отключено")
    }

    private fun printBackStack() {
        val backStackSize = supportFragmentManager.backStackEntryCount - 1
        for (index: Int in 0..backStackSize) {
            val fragmentName = supportFragmentManager.getBackStackEntryAt(index).name!!
            Log.e("ToolbarNavigator", "$index   $backStackSize   $fragmentName")
        }
    }

    override fun showFragment(fragment: Fragment) = toolbarNavigator.launchFragment(fragment)
}
