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
import com.kok1337.mobiledev.presentation.adapter.HighlightedDictionaryAdapter
import com.kok1337.mobiledev.presentation.adapter.SectionAdapter
import com.kok1337.mobiledev.presentation.dialog.ConfirmDialog
import com.kok1337.mobiledev.presentation.dialog.SelectTaxParamsDialog
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.model.AddressUIModel
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.setItemsAndTryAutoSelect
import com.kok1337.mobiledev.presentation.util.showDialog
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import javax.inject.Inject

class AddressFragment : Fragment(R.layout.fragment_tax_address) {

    private val uiModel = AddressUIModel()

    private val binding by viewBinding(FragmentTaxAddressBinding::bind)

    @Inject
    lateinit var viewModelFactory: AddressViewModel.Factory
    private lateinit var viewModel: AddressViewModel
    private val tabViewModel: TaxTabViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AddressViewModel::class.java]
        super.onAttach(context)
    }

    private val federalDistrictAdapter = DictionaryAdapter<FederalDistrictItem>()
    private val federalDistrictConf =
        SearchableSpinner.SearchableSpinnerConfiguration(federalDistrictAdapter) {
            viewModel.onFederalDistrictItemSelected(it)
        }

    private val subjectOfRusFedAdapter = DictionaryAdapter<SubjectOfRusFedItem>()
    private val subjectOfRusConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subjectOfRusFedAdapter) {
            viewModel.onSubjectOfRusFedItemSelected(it)
        }

    private val forestryAdapter = DictionaryAdapter<ForestryItem>()
    private val forestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(forestryAdapter) {
            viewModel.onForestryItemSelected(it)
        }

    private val localForestryAdapter = DictionaryAdapter<LocalForestryItem>()
    private val localForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(localForestryAdapter) {
            viewModel.onLocalForestryItemSelected(it)
        }

    private val subForestryAdapter = DictionaryAdapter<SubForestryItem>()
    private val subForestryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(subForestryAdapter) {
            viewModel.onSubForestryItemSelected(it)
        }

    private val areaAdapter = HighlightedDictionaryAdapter<AreaItem>()
    private val areaConf =
        SearchableSpinner.SearchableSpinnerConfiguration(areaAdapter, AreaItem.getSortTypes()) {
            viewModel.onAreaItemSelected(it)
        }

    private val sectionAdapter = SectionAdapter()
    private val sectionConf =
        SearchableSpinner.SearchableSpinnerConfiguration(
            sectionAdapter, SectionItem.getSortTypes()
        ) { viewModel.onSectionItemSelected(it) }

    private val taxSourceAdapter = DictionaryAdapter<TaxSourceItem>()
    private val taxSourceConf =
        SearchableSpinner.SearchableSpinnerConfiguration(taxSourceAdapter) {
            viewModel.onTaxSourceItemSelected(it)
        }

    private val taxYearAdapter = HighlightedDictionaryAdapter<TaxYearItem>()
    private val taxYearConf =
        SearchableSpinner.SearchableSpinnerConfiguration(
            taxYearAdapter,
            TaxYearItem.getSortTypes()
        ) {
            viewModel.onTaxYearItemSelected(it)
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.uim = uiModel

        initUiModelObservers()
        initSelectedItemLdObservers()
        initItemsLdObservers()
        setSpinnerConfigurations()

        binding.addTaxButton.setOnClickListener { showSelectTaxParamsDialog() }
        binding.delTaxButton.setOnClickListener { showConfirmDeleteInfoTaxDialog() }

        viewModel.startLoad()
    }

    private fun showSelectTaxParamsDialog() {
        val dialog = SelectTaxParamsDialog(
            viewModel.allTaxSourceLD, viewModel.sectionSLD.selectedItem,
            viewModel.taxSourceSLD.selectedItem, viewModel.taxYearSLD.selectedItem
        ) { sectionItem, taxSourceItem, year ->
            viewModel.trySaveInfoTax(sectionItem, taxSourceItem, year)
        }
        showDialog(dialog, requireContext())
    }

    private fun showConfirmDeleteInfoTaxDialog() {
        val dialog = ConfirmDialog("Вы действительно хотите удалить?") {
            viewModel.taxYearSLD.selectedItem?.let { viewModel.deletedInfoTaxByTaxYear(it) }
        }
        showDialog(dialog, requireContext())
    }

    private fun initUiModelObservers() {
        viewModel.federalDistrictSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.federalDistrictListSize = it }
        viewModel.subjectOfRusFedSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.subjectOfRusFedListSize = it }
        viewModel.forestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.forestryListSize = it  }
        viewModel.localForestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.localForestryListSize = it }
        viewModel.subForestrySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.subForestryListSize = it }
        viewModel.areaSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.areaListSize = it }
        viewModel.sectionSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.sectionListSize = it }
        viewModel.taxSourceSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.taxSourceListSize = it }
        viewModel.taxYearSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.taxYearListSize = it }
        viewModel.areaSLD.selectedItemLD.observe(viewLifecycleOwner)
        { uiModel.isSelectedAreaNotNull = it != null }
        viewModel.isDeletedInfoTaxLD.observe(viewLifecycleOwner)
        { uiModel.isCanBeDeleted = !it }

        tabViewModel.taxIdLD.observe(viewLifecycleOwner)
        { uiModel.isSelectedTaxNotNull = it != null }
        mainViewModel.editEnabledLD.observe(viewLifecycleOwner)
        { uiModel.isEdit = it }
    }

    private fun initSelectedItemLdObservers() {
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

        viewModel.taxYearSLD.selectedItemLD.observe(viewLifecycleOwner) {
            taxYearConf.selectedItem = it
            tabViewModel.setTaxId(it?.taxId)
        }
    }

    private fun initItemsLdObservers() {
        viewModel.federalDistrictSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.federalDistrictSpinner, federalDistrictAdapter, it) }
        viewModel.subjectOfRusFedSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.subjectOfRusFedSpinner, subjectOfRusFedAdapter, it) }
        viewModel.forestrySLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.forestrySpinner, forestryAdapter, it) }
        viewModel.localForestrySLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.localForestrySpinner, localForestryAdapter, it) }
        viewModel.subForestrySLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.subForestrySpinner, subForestryAdapter, it) }
        viewModel.areaSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.areaSpinner, areaAdapter, it) }
        viewModel.sectionSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.sectionSpinner, sectionAdapter, it) }
        viewModel.taxSourceSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.taxSourceSpinner, taxSourceAdapter, it) }
        viewModel.taxYearSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.taxYearSpinner, taxYearAdapter, it) }
    }

    private fun setSpinnerConfigurations() {
        binding.federalDistrictSpinner.searchableSpinnerConfiguration = federalDistrictConf
        binding.subjectOfRusFedSpinner.searchableSpinnerConfiguration = subjectOfRusConf
        binding.forestrySpinner.searchableSpinnerConfiguration = forestryConf
        binding.localForestrySpinner.searchableSpinnerConfiguration = localForestryConf
        binding.subForestrySpinner.searchableSpinnerConfiguration = subForestryConf
        binding.areaSpinner.searchableSpinnerConfiguration = areaConf
        binding.sectionSpinner.searchableSpinnerConfiguration = sectionConf
        binding.taxSourceSpinner.searchableSpinnerConfiguration = taxSourceConf
        binding.taxYearSpinner.searchableSpinnerConfiguration = taxYearConf
    }
}