package com.kok1337.mobiledev.presentation.fragment.taxation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxTabBinding

class TaxTabFragment : Fragment(R.layout.fragment_tax_tab) {

    private val binding by viewBinding(FragmentTaxTabBinding::bind)
    private val viewModel: TaxTabViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHost =
            childFragmentManager.findFragmentById(R.id.taxTabsContainer) as NavHostFragment
        val navController = navHost.navController
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)

        viewModel.taxIdLD.observe(viewLifecycleOwner) {
            val taxIdNotNull = it != null
            navView.menu.findItem(R.id.tax_characteristic).isEnabled = taxIdNotNull
            navView.menu.findItem(R.id.tax_layer).isEnabled = taxIdNotNull
        }
    }
}