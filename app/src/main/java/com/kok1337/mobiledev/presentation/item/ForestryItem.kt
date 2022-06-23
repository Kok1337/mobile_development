package com.kok1337.mobiledev.presentation.item

data class ForestryItem(val id: Int, val value: String) : DictionaryItem {
    override fun getName(): String = value
}