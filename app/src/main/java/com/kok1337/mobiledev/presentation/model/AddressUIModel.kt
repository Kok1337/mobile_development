package com.kok1337.mobiledev.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.ForestryItem
import com.kok1337.mobiledev.presentation.item.SubjectOfRusFedItem

class AddressUIModel(
    private var _federalDistrictListSize: Int = 0,
    private var _subjectOfRusFedListSize: Int = 0,
    private var _forestryListSize: Int = 0,
) : BaseObservable() {

    val federalDistrictListObserver: (List<FederalDistrictItem>) -> Unit = { federalDistrictListSize = it.size }
    var federalDistrictListSize: Int
        @Bindable get() = _federalDistrictListSize
        set(value) {
            _federalDistrictListSize = value
            notifyPropertyChanged(BR.federalDistrictListSize)
        }

    val subjectOfRusFedListObserver: (List<SubjectOfRusFedItem>) -> Unit = { subjectOfRusFedListSize = it.size }
    var subjectOfRusFedListSize: Int
        @Bindable get() = _subjectOfRusFedListSize
        set(value) {
            _subjectOfRusFedListSize = value
            notifyPropertyChanged(BR.subjectOfRusFedListSize)
        }

    val forestryListObserver: (List<ForestryItem>) -> Unit = { forestryListSize = it.size }
    var forestryListSize: Int
        @Bindable get() = _forestryListSize
        set(value) {
            _forestryListSize = value
            notifyPropertyChanged(BR.forestryListSize)
        }
}