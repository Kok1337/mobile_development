package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.view.searchablespinner.SpinnerItem

data class FederalDistrictItem(val id: Int, val value: String) : DictionaryItem, SpinnerItem {
    override fun getName(): String = value
    override fun getSpinnerText(): String = value
}