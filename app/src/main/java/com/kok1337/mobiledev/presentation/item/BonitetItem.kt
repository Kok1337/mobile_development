package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.adapter.DictionaryItem

data class BonitetItem(val id: Int, val value: String) : DictionaryItem {
    override fun getName(): String = value
}