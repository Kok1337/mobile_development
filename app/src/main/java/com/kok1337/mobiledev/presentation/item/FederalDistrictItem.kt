package com.kok1337.mobiledev.presentation.item

data class FederalDistrictItem(val id: Int, val value: String) : DictionaryItem {
    override fun getName(): String = value
}