package com.kok1337.mobiledev.presentation.item

import androidx.databinding.Bindable

data class FederalDistrictItem(val id: Int, val value: String) : DictionaryItem() {
    @Bindable
    override var name = value
}