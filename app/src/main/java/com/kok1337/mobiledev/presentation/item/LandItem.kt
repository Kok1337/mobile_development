package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.adapter.DictionaryItem

data class LandItem(val id: Int, val value: String, val isForestLand: Boolean) : DictionaryItem {
    override fun getName(): String = value
}