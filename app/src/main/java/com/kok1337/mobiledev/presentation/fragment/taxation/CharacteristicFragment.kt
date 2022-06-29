package com.kok1337.mobiledev.presentation.fragment.taxation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.FragmentTaxCharacteristicBinding
import com.kok1337.mobiledev.presentation.util.getAppComponent
import javax.inject.Inject

class CharacteristicFragment : Fragment(R.layout.fragment_tax_characteristic) {

    private val binding by viewBinding(FragmentTaxCharacteristicBinding::bind)

    @Inject
    lateinit var viewModelFactory: CharacteristicViewModel.Factory
    private lateinit var viewModel: CharacteristicViewModel

    override fun onAttach(context: Context) {
        getAppComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[CharacteristicViewModel::class.java]
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}