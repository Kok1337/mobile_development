package com.kok1337.mobiledev.presentation.item

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kok1337.mobiledev.domain.enum.Work

data class WorkTypeItem(
    val work: Work,
    @Bindable val name: String,
) : BaseObservable() {

}