package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.adapter.DictionaryItem

data class OriginItem(val id: Int, val n: String, val value: Boolean?) : DictionaryItem {
    override fun getName(): String = n
}