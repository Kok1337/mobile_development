package com.kok1337.mobiledev.presentation.fragment.trialarea

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaTabBinding

class TaTabFragment : Fragment(R.layout.fragment_ta_tab) {

    private val binding by viewBinding(FragmentTaTabBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavigation()
    }

    private fun initNavigation() {
        val navHost =
            childFragmentManager.findFragmentById(R.id.taTabsContainer) as NavHostFragment
        val navController = navHost.navController
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)
    }
}