package com.kok1337.mobiledev.presentation.fragment.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxTabBinding
import com.kok1337.mobiledev.databinding.FragmentTbWorktypesBinding

class WorkTypesFragment : Fragment(R.layout.fragment_tb_worktypes) {

    private var _binding: FragmentTbWorktypesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTbWorktypesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val navHost = parentFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = findNavController()

        binding.tax.setOnClickListener { navController.navigate(R.id.action_tb_workTypesFragment_to_taxTabFragment) }

        binding.ta.setOnClickListener { navController.navigate(R.id.action_tb_workTypesFragment_to_taTabFragment) }
    }

}