package com.kok1337.mobiledev.presentation.fragment.taxation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxAddressBinding
import com.kok1337.mobiledev.presentation.MainViewModel
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.ForestryItem
import com.kok1337.mobiledev.presentation.item.SubjectOfRusFedItem
import com.kok1337.mobiledev.presentation.mapper.toModel
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.setItemsAndTryAutoSelect
import com.kok1337.mobiledev.presentation.util.showToast
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import javax.inject.Inject

class AddressFragment : Fragment(R.layout.fragment_tax_address) {

    private val binding by viewBinding(FragmentTaxAddressBinding::bind)

    @Inject
    lateinit var viewModelFactory: AddressViewModel.Factory
    private lateinit var viewModel: AddressViewModel

    private val federalDistrictAdapter = DictionaryAdapter<FederalDistrictItem>()
    private val federalDistrictConf = SearchableSpinner.SearchableSpinnerConfiguration(federalDistrictAdapter) {
        if (viewModel.federalDistrictSelectedItem.value == it) return@SearchableSpinnerConfiguration
        viewModel.setFederalDistrictSelectedItem(it)
        viewModel.resetSubjectOfRusFedList()
        it?.let { viewModel.getAllSubjectOfRusFedByFederalDistrict(it.toModel()) }
    }

    private val subjectOfRusFedAdapter = DictionaryAdapter<SubjectOfRusFedItem>()
    private val subjectOfRusConf = SearchableSpinner.SearchableSpinnerConfiguration(subjectOfRusFedAdapter) {
        if (viewModel.subjectOfRusFedSelectedItem.value == it) return@SearchableSpinnerConfiguration
        viewModel.setSubjectOfRusFedSelectedItem(it)
        viewModel.resetForestryList()
        it?.let { viewModel.getAllForestryBySubjectOfRusFed(it.toModel()) }
    }

    private val forestryAdapter = DictionaryAdapter<ForestryItem>()
    private val forestryConf = SearchableSpinner.SearchableSpinnerConfiguration(forestryAdapter) {
        if (viewModel.forestrySelectedItem.value == it) return@SearchableSpinnerConfiguration
        viewModel.setForestrySelectedItem(it)
    }

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddressViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showToast("Экран создан")

        with(binding) {
            addressUIModel = viewModel.addressUIModel.value

            federalDistrictSpinner.searchableSpinnerConfiguration = federalDistrictConf
            subjectOfRusFedSpinner.searchableSpinnerConfiguration = subjectOfRusConf
            forestrySpinner.searchableSpinnerConfiguration = forestryConf

            viewModel.federalDistrictListLiveData.observe(viewLifecycleOwner) { setItemsAndTryAutoSelect(federalDistrictSpinner, federalDistrictAdapter, it) }
            viewModel.subjectOfRusFedListLiveData.observe(viewLifecycleOwner) { setItemsAndTryAutoSelect(subjectOfRusFedSpinner, subjectOfRusFedAdapter, it) }
            viewModel.forestryListLiveData.observe(viewLifecycleOwner) { setItemsAndTryAutoSelect(forestrySpinner, forestryAdapter, it) }
        }


        viewModel.getAllFederalDistrict()
    }
}


/*
    private val mainViewModel: MainViewModel by activityViewModels()

    mainViewModel.currentTbDirectionLD.observe(viewLifecycleOwner) {
        showToast("Погодите. Это реально?")
    }
 */