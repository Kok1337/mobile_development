package com.kok1337.mobiledev.presentation.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class DictionaryItem(
    val id: Int,
    @Bindable val name: String,
) : BaseObservable() {

}