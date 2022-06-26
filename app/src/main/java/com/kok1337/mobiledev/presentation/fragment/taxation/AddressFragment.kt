package com.kok1337.mobiledev.presentation.fragment.taxation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxAddressBinding
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.adapter.HighlightedDictionaryAdapter
import com.kok1337.mobiledev.presentation.adapter.SectionAdapter
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.model.AddressUIModel
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
            if (viewModel.federalDistrictSLD.trySetNewItem(it)) {
                viewModel.getAllSubjectOfRusFedByFederalDistrict()
            }
        }

    private val subjectOfRusFedAdapter = DictionaryAdapter<SubjectOfRusFedItem>()
    private val subjectOfRusConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subjectOfRusFedAdapter) {
            if (viewModel.subjectOfRusFedSLD.trySetNewItem(it))
                viewModel.getAllForestryBySubjectOfRusFed()
        }

    private val forestryAdapter = DictionaryAdapter<ForestryItem>()
    private val forestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(forestryAdapter) {
            if (viewModel.forestrySLD.trySetNewItem(it))
                viewModel.getAllLocalForestryByForestry()
        }

    private val localForestryAdapter = DictionaryAdapter<LocalForestryItem>()
    private val localForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(localForestryAdapter) {
            if (viewModel.localForestrySLD.trySetNewItem(it))
                viewModel.getAllSubForestryByLocalForestry()
        }

    private val subForestryAdapter = DictionaryAdapter<SubForestryItem>()
    private val subForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subForestryAdapter) {
            if (viewModel.subForestrySLD.trySetNewItem(it))
                viewModel.getAllAreaByAreaParams()
        }

    private val areaAdapter = HighlightedDictionaryAdapter<AreaItem>()
    private val areaConf =
        SearchableSpinner.SearchableSpinnerConfiguration(areaAdapter, AreaItem.getSortTypes()) {
            if (viewModel.areaSLD.trySetNewItem(it))
                viewModel.getAllSectionByArea()
        }

    private val sectionAdapter = SectionAdapter()
    private val sectionConf =
        SearchableSpinner.SearchableSpinnerConfiguration(
            sectionAdapter, SectionItem.getSortTypes()
        ) {
            if (viewModel.sectionSLD.trySetNewItem(it))
                viewModel.getAllTaxSourceByAreaAndSection()
        }

    private val taxSourceAdapter = DictionaryAdapter<TaxSourceItem>()
    private val taxSourceConf =
        SearchableSpinner.SearchableSpinnerConfiguration(taxSourceAdapter) {
            viewModel.taxSourceSLD.trySetNewItem(it)
        }

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddressViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uiModel = AddressUIModel()

        viewModel.federalDistrictSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.federalDistrictListSize.set(it) }
        viewModel.subjectOfRusFedSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.subjectOfRusFedListSize.set(it) }
        viewModel.forestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.forestryListSize.set(it) }
        viewModel.localForestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.localForestryListSize.set(it) }
        viewModel.subForestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.subForestryListSize.set(it) }
        viewModel.areaSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.areaListSize.set(it) }
        viewModel.sectionSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.sectionListSize.set(it) }
        viewModel.taxSourceSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.taxSourceListSize.set(it) }

        viewModel.federalDistrictSLD.selectedItemLD.observe(viewLifecycleOwner)
        { federalDistrictConf.selectedItem = it }
        viewModel.subjectOfRusFedSLD.selectedItemLD.observe(viewLifecycleOwner)
        { subjectOfRusConf.selectedItem = it }
        viewModel.forestrySLD.selectedItemLD.observe(viewLifecycleOwner)
        { forestryConf.selectedItem = it }
        viewModel.localForestrySLD.selectedItemLD.observe(viewLifecycleOwner)
        { localForestryConf.selectedItem = it }
        viewModel.subForestrySLD.selectedItemLD.observe(viewLifecycleOwner)
        { subForestryConf.selectedItem = it }
        viewModel.areaSLD.selectedItemLD.observe(viewLifecycleOwner)
        { areaConf.selectedItem = it }
        viewModel.sectionSLD.selectedItemLD.observe(viewLifecycleOwner)
        { sectionConf.selectedItem = it }
        viewModel.taxSourceSLD.selectedItemLD.observe(viewLifecycleOwner)
        { taxSourceConf.selectedItem = it }

        with(binding) {
            binding.uim = uiModel

            viewModel.federalDistrictSLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(federalDistrictSpinner, federalDistrictAdapter, it) }
            viewModel.subjectOfRusFedSLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(subjectOfRusFedSpinner, subjectOfRusFedAdapter, it) }
            viewModel.forestrySLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(forestrySpinner, forestryAdapter, it) }
            viewModel.localForestrySLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(localForestrySpinner, localForestryAdapter, it) }
            viewModel.subForestrySLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(subForestrySpinner, subForestryAdapter, it) }
            viewModel.areaSLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(areaSpinner, areaAdapter, it) }
            viewModel.sectionSLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(sectionSpinner, sectionAdapter, it) }
            viewModel.taxSourceSLD.itemsLD.observe(viewLifecycleOwner)
            { setItemsAndTryAutoSelect(taxSourceSpinner, taxSourceAdapter, it) }

            federalDistrictSpinner.searchableSpinnerConfiguration = federalDistrictConf
            subjectOfRusFedSpinner.searchableSpinnerConfiguration = subjectOfRusConf
            forestrySpinner.searchableSpinnerConfiguration = forestryConf
            localForestrySpinner.searchableSpinnerConfiguration = localForestryConf
            subForestrySpinner.searchableSpinnerConfiguration = subForestryConf
            areaSpinner.searchableSpinnerConfiguration = areaConf
            sectionSpinner.searchableSpinnerConfiguration = sectionConf
            taxSourceSpinner.searchableSpinnerConfiguration = taxSourceConf
        }

        if (viewModel.federalDistrictSLD.selectedItem == null) viewModel.getAllFederalDistrict()
    }
}


/*
    private val mainViewModel: MainViewModel by activityViewModels()

    mainViewModel.currentTbDirectionLD.observe(viewLifecycleOwner) {
        showToast("Погодите. Это реально?")
    }
 */