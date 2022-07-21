package com.kok1337.mobiledev.presentation.fragment.toolbar

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTbWorktypesBinding
import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.presentation.adapter.WorkTypeAdapter
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.RecyclerConfiguration
import com.kok1337.mobiledev.presentation.fragment.taxation.TaxTabFragment
import com.kok1337.mobiledev.presentation.fragment.trialarea.TaTabFragment
import com.kok1337.mobiledev.presentation.item.WorkTypeItem
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.navigator
import javax.inject.Inject

class WorkTypesFragment : Fragment(R.layout.fragment_tb_worktypes) {

    private val binding by viewBinding(FragmentTbWorktypesBinding::bind)

    @Inject
    lateinit var viewModelFactory: WorkTypesViewModel.Factory
    private lateinit var viewModel: WorkTypesViewModel

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[WorkTypesViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workTypeAdapter = WorkTypeAdapter { _, item -> openSelectedWorkType(item) }
        viewModel.workTypesLiveData.observe(viewLifecycleOwner) { workTypeAdapter.setItems(it) }
        RecyclerConfiguration.Builder()
            .bindingAdapter(workTypeAdapter)
            .layoutManager(LinearLayoutManager(context))
            .build().configure(binding.workTypeRecyclerView)

        viewModel.getAllWorkTypes()
    }

    private fun openSelectedWorkType(workTypeItem: WorkTypeItem) {
        when (workTypeItem.work) {
            Work.TRIAL_AREA -> navigator().showFragment(TaTabFragment())
            Work.TAXATION -> navigator().showFragment(TaxTabFragment())
        }
    }
}