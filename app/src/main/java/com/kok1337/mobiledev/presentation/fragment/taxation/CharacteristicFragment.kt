package com.kok1337.mobiledev.presentation.fragment.taxation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxCharacteristicBinding
import com.kok1337.mobiledev.presentation.MainViewModel
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.dialog.InputStringDialog
import com.kok1337.mobiledev.presentation.dialog.PreviewDialog
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.model.CharacteristicUIModel
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.setItemsAndTryAutoSelect
import com.kok1337.mobiledev.presentation.util.showDialog
import com.kok1337.mobiledev.presentation.util.showToast
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import javax.inject.Inject

class CharacteristicFragment : Fragment(R.layout.fragment_tax_characteristic) {

    private val uiModel = CharacteristicUIModel()

    private val binding by viewBinding(FragmentTaxCharacteristicBinding::bind)

    @Inject
    lateinit var viewModelFactory: CharacteristicViewModel.Factory
    private lateinit var viewModel: CharacteristicViewModel
    private val tabViewModel: TaxTabViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val isEdit: Boolean
        get() = mainViewModel.editEnabledLD.value ?: false

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacteristicViewModel::class.java]
        super.onAttach(context)
    }

    private val landCategoryAdapter = DictionaryAdapter<LandCategoryItem>()
    private val landCategoryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(landCategoryAdapter) {
            viewModel.onLandCategoryItemSelected(it)
        }

    private val targetCategoryAdapter = DictionaryAdapter<TargetCategoryItem>()
    private val targetCategoryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(targetCategoryAdapter) {
            viewModel.onTargetCategoryItemSelected(it)
        }

    private val protectionCategoryAdapter = DictionaryAdapter<ProtectionCategoryItem>()
    private val protectionCategoryConf =
        SearchableSpinner.SearchableSpinnerConfiguration(protectionCategoryAdapter) {
            viewModel.onProtectionCategoryItemSelected(it)
        }

    private val ooptAdapter = DictionaryAdapter<OoptItem>()
    private val ooptConf =
        SearchableSpinner.SearchableSpinnerConfiguration(ooptAdapter) {
            viewModel.onOoptItemSelected(it)
        }

    private val ozuAdapter = DictionaryAdapter<OzuItem>()
    private val ozuConf =
        SearchableSpinner.SearchableSpinnerConfiguration(ozuAdapter) {
            viewModel.onOzuItemSelected(it)
        }

    private val bonitetAdapter = DictionaryAdapter<BonitetItem>()
    private val bonitetConf =
        SearchableSpinner.SearchableSpinnerConfiguration(bonitetAdapter) {
            viewModel.onBonitetItemSelected(it)
        }

    private val tluAdapter = DictionaryAdapter<TluItem>()
    private val tluConf =
        SearchableSpinner.SearchableSpinnerConfiguration(tluAdapter) {
            viewModel.onTluItemSelected(it)
        }

    private val originAdapter = DictionaryAdapter<OriginItem>()
    private val originConf =
        SearchableSpinner.SearchableSpinnerConfiguration(originAdapter) {
            viewModel.onOriginItemSelected(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uim = uiModel

        initUiModelObservers()
        initSelectedItemLdObservers()
        initItemsLdObservers()
        setSpinnerConfigurations()

        // binding.ooptButton.setOnClickListener { openOoptInfoDialog() }
    }

    private fun openOoptInfoDialog() {
        val inputDialog = InputStringDialog("Введите ООПТ", "null", {
            return@InputStringDialog it != "123"
        }) {
            showToast(it)
        }
        val previewDialog = PreviewDialog("ООПТ", "null")
        showDialog(if (isEdit) inputDialog else previewDialog, requireContext())
    }

    private fun initUiModelObservers() {
        viewModel.landCategorySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.landCategoryListSize = it }
        viewModel.targetCategorySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.targetCategoryListSize = it }
        viewModel.protectionCategorySLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.protectionCategoryListSize = it }
        viewModel.ooptSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.ooptListSize = it }
        viewModel.ozuSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.ozuListSize = it }
        viewModel.bonitetSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.bonitetListSize = it }
        viewModel.tluSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.tluListSize = it }
        viewModel.originSLD.itemCountLD.observe(viewLifecycleOwner)
        { uiModel.originListSize = it }

        mainViewModel.editEnabledLD.observe(viewLifecycleOwner)
        { uiModel.isEdit = it }
    }

    private fun initSelectedItemLdObservers() {
        viewModel.landCategorySLD.selectedItemLD.observe(viewLifecycleOwner)
        { landCategoryConf.selectedItem = it }
        viewModel.targetCategorySLD.selectedItemLD.observe(viewLifecycleOwner)
        { targetCategoryConf.selectedItem = it }
        viewModel.protectionCategorySLD.selectedItemLD.observe(viewLifecycleOwner)
        { protectionCategoryConf.selectedItem = it }
        viewModel.ooptSLD.selectedItemLD.observe(viewLifecycleOwner)
        { ooptConf.selectedItem = it }
        viewModel.ozuSLD.selectedItemLD.observe(viewLifecycleOwner)
        { ozuConf.selectedItem = it }
        viewModel.bonitetSLD.selectedItemLD.observe(viewLifecycleOwner)
        { bonitetConf.selectedItem = it }
        viewModel.tluSLD.selectedItemLD.observe(viewLifecycleOwner)
        { tluConf.selectedItem = it }
        viewModel.originSLD.selectedItemLD.observe(viewLifecycleOwner)
        { originConf.selectedItem = it }
    }

    private fun initItemsLdObservers() {
        viewModel.landCategorySLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.landCategorySpinner, landCategoryAdapter, it) }
        viewModel.targetCategorySLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.targetCategorySpinner, targetCategoryAdapter, it) }
        viewModel.protectionCategorySLD.itemsLD.observe(viewLifecycleOwner) {
            setItemsAndTryAutoSelect(
                binding.protectionCategorySpinner, protectionCategoryAdapter, it
            )
        }
        viewModel.ooptSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.ooptSpinner, ooptAdapter, it) }
        viewModel.ozuSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.ozuSpinner, ozuAdapter, it) }
        viewModel.bonitetSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.bonitetSpinner, bonitetAdapter, it) }
        viewModel.tluSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.tluSpinner, tluAdapter, it) }
        viewModel.originSLD.itemsLD.observe(viewLifecycleOwner)
        { setItemsAndTryAutoSelect(binding.originAndLandSpinner, originAdapter, it) }
    }

    private fun setSpinnerConfigurations() {
        binding.landCategorySpinner.searchableSpinnerConfiguration = landCategoryConf
        binding.targetCategorySpinner.searchableSpinnerConfiguration = targetCategoryConf
        binding.protectionCategorySpinner.searchableSpinnerConfiguration = protectionCategoryConf
        binding.ooptSpinner.searchableSpinnerConfiguration = ooptConf
        binding.ozuSpinner.searchableSpinnerConfiguration = ozuConf
        binding.bonitetSpinner.searchableSpinnerConfiguration = bonitetConf
        binding.tluSpinner.searchableSpinnerConfiguration = tluConf
        binding.originAndLandSpinner.searchableSpinnerConfiguration = originConf
    }
}