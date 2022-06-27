package com.kok1337.mobiledev.presentation.model

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt

class AddressUIModel {
    val federalDistrictListSize = ObservableInt(0)
    val subjectOfRusFedListSize = ObservableInt(0)
    val forestryListSize = ObservableInt(0)
    val localForestryListSize = ObservableInt(0)
    val subForestryListSize = ObservableInt(0)
    val areaListSize = ObservableInt(0)
    val sectionListSize = ObservableInt(0)
    val taxSourceListSize = ObservableInt(0)
    val selectedTaxNotNull = ObservableBoolean(false)
}