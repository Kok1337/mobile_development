package com.kok1337.mobiledev.presentation.adapter

import com.kok1337.mobiledev.presentation.view.searchablespinner.SpinnerItem

interface DictionaryItem : SpinnerItem {
    fun getName(): String
    override fun getSpinnerText(): String = getName()
    override fun getSearchableString(): String = getName()
}