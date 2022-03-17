package com.kok1337.mobiledev.presentation.fragment.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTbWorktypesBinding
import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.presentation.adapter.WorkTypeAdapter
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.RecyclerConfiguration
import com.kok1337.mobiledev.presentation.fragment.getAppComponent
import com.kok1337.mobiledev.presentation.item.WorkTypeItem
import javax.inject.Inject

class WorkTypesFragment : Fragment(R.layout.fragment_tb_worktypes) {

    private var _binding: FragmentTbWorktypesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: WorkTypesViewModel.Factory
    private lateinit var workTypesViewModel: WorkTypesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        getAppComponent().inject(this)
        workTypesViewModel =
            ViewModelProvider(this, viewModelFactory)[WorkTypesViewModel::class.java]
        _binding = FragmentTbWorktypesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workTypeAdapter = WorkTypeAdapter { _, item -> openSelectedWorkType(item) }
        workTypesViewModel.workTypesLiveData.observe(viewLifecycleOwner) { workTypeAdapter.setItems(it) }
        RecyclerConfiguration.Builder()
            .bindingAdapter(workTypeAdapter)
            .layoutManager(LinearLayoutManager(context))
            .build().configure(binding.workTypeRecyclerView)

        workTypesViewModel.getAllWorkTypes()
    }

    private fun openSelectedWorkType(workTypeItem: WorkTypeItem) {
        val navController = findNavController()
        when (workTypeItem.work) {
            Work.TRIAL_AREA -> navController.navigate(WorkTypesFragmentDirections.actionTbWorkTypesFragmentToTaTabFragment())
            Work.TAXATION -> navController.navigate(WorkTypesFragmentDirections.actionTbWorkTypesFragmentToTaxTabFragment())
        }
    }
}