package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.view.searchablespinner.SpinnerItem

interface DictionaryItem : SpinnerItem {
    fun getName(): String
    override fun getSpinnerText(): String = getName()
}