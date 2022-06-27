package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TaxTabViewModel : ViewModel() {

    private val _taxIdLD = MutableLiveData<UUID?>()
    val taxIdLD : LiveData<UUID?> = _taxIdLD
    fun setTaxId(taxId: UUID?) {
        _taxIdLD.value = taxId
    }

}