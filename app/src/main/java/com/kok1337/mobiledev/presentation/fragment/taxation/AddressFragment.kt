package com.kok1337.mobiledev.presentation.fragment.taxation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxAddressBinding
import com.kok1337.mobiledev.databinding.ItemSectionBinding
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.adapter.HighlightedDictionaryAdapter
import com.kok1337.mobiledev.presentation.adapter.SectionAdapter
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.setItemsAndTryAutoSelect
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import javax.inject.Inject

class AddressFragment : Fragment(R.layout.fragment_tax_address) {

    private val binding by viewBinding(FragmentTaxAddressBinding::bind)

    @Inject
    lateinit var viewModelFactory: AddressViewModel.Factory
    private lateinit var viewModel: AddressViewModel

    private val federalDistrictAdapter = DictionaryAdapter<FederalDistrictItem>()
    private val federalDistrictConf =
        SearchableSpinner.SearchableSpinnerConfiguration(federalDistrictAdapter) {
            if (viewModel.federalDistrictSelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setFederalDistrictSelectedItem(it)
            viewModel.resetSubjectOfRusFedList()
            it?.let { viewModel.getAllSubjectOfRusFedByFederalDistrict(it) }
        }

    private val subjectOfRusFedAdapter = DictionaryAdapter<SubjectOfRusFedItem>()
    private val subjectOfRusConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subjectOfRusFedAdapter) {
            if (viewModel.subjectOfRusFedSelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setSubjectOfRusFedSelectedItem(it)
            viewModel.resetForestryList()
            it?.let { viewModel.getAllForestryBySubjectOfRusFed(it) }
        }

    private val forestryAdapter = DictionaryAdapter<ForestryItem>()
    private val forestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(forestryAdapter) {
            if (viewModel.forestrySelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setForestrySelectedItem(it)
            viewModel.resetLocalForestryList()
            it?.let { viewModel.getAllLocalForestryByForestry(it) }
        }

    private val localForestryAdapter = DictionaryAdapter<LocalForestryItem>()
    private val localForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(localForestryAdapter) {
            if (viewModel.localForestrySelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setLocalForestrySelectedItem(it)
            viewModel.resetSubForestryList()
            it?.let { viewModel.getAllSubForestryByLocalForestry(it) }
        }

    private val subForestryAdapter = DictionaryAdapter<SubForestryItem>()
    private val subForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subForestryAdapter) {
            if (viewModel.subForestrySelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setSubForestrySelectedItem(it)
            viewModel.resetAreaList()
            it?.let {
                with(binding) {
                    val areaParamsItem = AreaParamsItem(
                        federalDistrictSpinner.getSelectedItem()!!,
                        subjectOfRusFedSpinner.getSelectedItem()!!,
                        forestrySpinner.getSelectedItem()!!,
                        localForestrySpinner.getSelectedItem()!!,
                        it
                    )
                    viewModel.getAllAreaByAreaParams(areaParamsItem)
                }
            }
        }

    private val areaAdapter = HighlightedDictionaryAdapter<AreaItem>()
    private val areaConf =
        SearchableSpinner.SearchableSpinnerConfiguration(areaAdapter, AreaItem.getSortTypes()) {
            if (viewModel.areaSelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setAreaSelectedItem(it)
            viewModel.resetSectionList()
            it?.let { viewModel.getAllSectionByArea(it) }
        }

    private val sectionAdapter = SectionAdapter()
    private val sectionConf =
        SearchableSpinner.SearchableSpinnerConfiguration(sectionAdapter, SectionItem.getSortTypes()) {
            if (viewModel.sectionSelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setSectionSelectedItem(it)
            viewModel.resetTaxSourceList()
            it?.let {
                val area: AreaItem = binding.areaSpinner.getSelectedItem()!!
                viewModel.getAllTaxSource(area, it)
            }
        }

    private val taxSourceAdapter = DictionaryAdapter<TaxSourceItem>()
    private val taxSourceConf =
        SearchableSpinner.SearchableSpinnerConfiguration(taxSourceAdapter) {
            if (viewModel.taxSourceSelectedItem.value == it) return@SearchableSpinnerConfiguration
            viewModel.setTaxSourceSelectedItem(it)
        }



    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddressViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.federalDistrictSelectedItem.observe(viewLifecycleOwner) {
            federalDistrictConf.selectedItem = it
        }
        viewModel.subjectOfRusFedSelectedItem.observe(viewLifecycleOwner) {
            subjectOfRusConf.selectedItem = it
        }
        viewModel.forestrySelectedItem.observe(viewLifecycleOwner) {
            forestryConf.selectedItem = it
        }
        viewModel.localForestrySelectedItem.observe(viewLifecycleOwner) {
            localForestryConf.selectedItem = it
        }
        viewModel.subForestrySelectedItem.observe(viewLifecycleOwner) {
            subForestryConf.selectedItem = it
        }
        viewModel.areaSelectedItem.observe(viewLifecycleOwner) {
            areaConf.selectedItem = it
        }
        viewModel.sectionSelectedItem.observe(viewLifecycleOwner) {
            sectionConf.selectedItem = it
        }
        viewModel.taxSourceSelectedItem.observe(viewLifecycleOwner) {
            taxSourceConf.selectedItem = it
        }

        with(binding) {

            viewModel.addressUIModel.observe(viewLifecycleOwner) { addressUIModel = it }

            federalDistrictSpinner.searchableSpinnerConfiguration = federalDistrictConf
            subjectOfRusFedSpinner.searchableSpinnerConfiguration = subjectOfRusConf
            forestrySpinner.searchableSpinnerConfiguration = forestryConf
            localForestrySpinner.searchableSpinnerConfiguration = localForestryConf
            subForestrySpinner.searchableSpinnerConfiguration = subForestryConf
            areaSpinner.searchableSpinnerConfiguration = areaConf
            sectionSpinner.searchableSpinnerConfiguration = sectionConf
            taxSourceSpinner.searchableSpinnerConfiguration = taxSourceConf

            viewModel.federalDistrictListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(federalDistrictSpinner, federalDistrictAdapter, it)
            }
            viewModel.subjectOfRusFedListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(subjectOfRusFedSpinner, subjectOfRusFedAdapter, it)
            }
            viewModel.forestryListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(forestrySpinner, forestryAdapter, it)
            }
            viewModel.localForestryListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(localForestrySpinner, localForestryAdapter, it)
            }
            viewModel.subForestryListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(subForestrySpinner, subForestryAdapter, it)
            }
            viewModel.areaListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(areaSpinner, areaAdapter, it)
            }
            viewModel.sectionListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(sectionSpinner, sectionAdapter, it)
            }
            viewModel.taxSourceListLiveData.observe(viewLifecycleOwner) {
                setItemsAndTryAutoSelect(taxSourceSpinner, taxSourceAdapter, it)
            }
        }

        if (viewModel.federalDistrictSelectedItem.value == null) viewModel.getAllFederalDistrict()
    }
}


/*
    private val mainViewModel: MainViewModel by activityViewModels()

    mainViewModel.currentTbDirectionLD.observe(viewLifecycleOwner) {
        showToast("Погодите. Это реально?")
    }
 */