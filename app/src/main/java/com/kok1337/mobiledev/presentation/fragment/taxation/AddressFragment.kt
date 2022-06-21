package com.kok1337.mobiledev.presentation.fragment.taxation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxAddressBinding
import com.kok1337.mobiledev.presentation.MainViewModel
import com.kok1337.mobiledev.presentation.adapter.DictionaryAdapter
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.util.getAppComponent
import com.kok1337.mobiledev.presentation.util.showToast
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import javax.inject.Inject

class AddressFragment : Fragment(R.layout.fragment_tax_address) {

    private var _binding: FragmentTaxAddressBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: AddressViewModel.Factory
    private lateinit var addressViewModel: AddressViewModel
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        getAppComponent().inject(this)
        addressViewModel = ViewModelProvider(this, viewModelFactory)[AddressViewModel::class.java]

        _binding = FragmentTaxAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentTbDirectionLD.observe(viewLifecycleOwner) {
            showToast("Погодите. Это реально?")
        }

        val federalDistrictAdapter = DictionaryAdapter<FederalDistrictItem>()
        addressViewModel.federalDistrictsMutableLiveData.observe(viewLifecycleOwner) { federalDistrictAdapter.setItems(it)
            Log.e("tag", "dfdfhdfhdfhdfhd")
        }

        binding.federalDistrictSpinner.searchableSpinnerConfiguration = SearchableSpinner
            .SearchableSpinnerConfiguration
            .Builder(federalDistrictAdapter).itemSelectedListener({ item, position ->  }).build()

        addressViewModel.getAllFederalDistricts()
    }
}