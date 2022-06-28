package com.kok1337.mobiledev.presentation.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.DialogSelectTaxParamsBinding
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.item.SectionItem
import com.kok1337.mobiledev.presentation.item.TaxSourceItem
import com.kok1337.mobiledev.presentation.item.TaxYearItem
import com.kok1337.mobiledev.presentation.util.AppTextWatcher
import com.kok1337.mobiledev.presentation.util.setItemsAndTryAutoSelect
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner

class SelectTaxParamsDialog(
    private val taxSourceLiveData: LiveData<List<TaxSourceItem>>,
    private val selectedSectionItem: SectionItem?,
    private val selectedTaxSourceItem: TaxSourceItem?,
    private val selectedTaxYearItem: TaxYearItem?,
    private val onTaxParamsSelected: (sectionItem: SectionItem, taxSourceItem: TaxSourceItem, year: Int) -> Unit
) : DialogFragment(R.layout.dialog_select_tax_params) {

    companion object {
        const val TAG: String = "CreateTaxDialog"
    }

    private val binding by viewBinding(DialogSelectTaxParamsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setupView()
    }

    private fun setupSpinner() {
        val taxSourceAdapter = DictionaryAdapter<TaxSourceItem>()
        val taxSourceConf =
            SearchableSpinner.SearchableSpinnerConfiguration(
                bindingAdapter = taxSourceAdapter,
                selectedItem = selectedTaxSourceItem,
            ) { updateAcceptButtonEnable() }
        taxSourceLiveData.observe(viewLifecycleOwner) {
            setItemsAndTryAutoSelect(binding.taxSourceSpinner, taxSourceAdapter, it)
        }
        binding.taxSourceSpinner.searchableSpinnerConfiguration = taxSourceConf
    }

    private fun setupView() {
        binding.sectionEditText.setText(selectedSectionItem?.name ?: "")
        binding.yearEditText.setText(selectedTaxYearItem?.year?.toString() ?: "")
        binding.squareEditText.setText(selectedSectionItem?.s?.toString() ?: "")

        binding.sectionEditText.addTextChangedListener(AppTextWatcher {
            updateAcceptButtonEnable()
        })
        binding.yearEditText.addTextChangedListener(AppTextWatcher {
            updateAcceptButtonEnable()
        })
        binding.squareEditText.addTextChangedListener(AppTextWatcher {
            updateAcceptButtonEnable()
        })
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
        binding.acceptButton.setOnClickListener {
            val section: Int = binding.sectionEditText.text.toString().toInt()
            val year: Int = binding.yearEditText.text.toString().toInt()
            val square: Double = binding.squareEditText.text.toString().toDouble()
            val taxSource: TaxSourceItem = binding.taxSourceSpinner.getSelectedItem()!!
            val sectionItem = SectionItem(section.toString(), square)
            onTaxParamsSelected(sectionItem, taxSource, year)
            dismiss()
        }
        binding.acceptButton.isEnabled = false
    }

    private fun updateAcceptButtonEnable() {
        val section: Int? = binding.sectionEditText.text.toString().toIntOrNull()
        val year: Int? = binding.yearEditText.text.toString().toIntOrNull()
        val square: Double? = binding.squareEditText.text.toString().toDoubleOrNull()
        val taxSource: TaxSourceItem? = binding.taxSourceSpinner.getSelectedItem()

        val isNotNull = section != null && year != null && square != null && taxSource != null
        if (!isNotNull) {
            binding.acceptButton.isEnabled = false
            return
        }

        val isCorrectValue =  year!! > 1000 && square!! > 0
        if (!isCorrectValue) {
            binding.acceptButton.isEnabled = false
            return
        }

        val sectionItem = SectionItem(section.toString(), square!!)
        val isDifferent = selectedSectionItem != sectionItem ||
                selectedTaxSourceItem != taxSource ||
                year != selectedTaxYearItem?.year

        binding.acceptButton.isEnabled = isDifferent
    }

}