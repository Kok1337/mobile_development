package com.kok1337.mobiledev.presentation.fragment.trialarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaTabBinding
import com.kok1337.mobiledev.databinding.FragmentTaxTabBinding

class TabFragment : Fragment(R.layout.fragment_ta_tab) {

    private var _binding: FragmentTaTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost = childFragmentManager.findFragmentById(R.id.taTabsContainer) as NavHostFragment
        val navController = navHost.navController
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)
    }
}