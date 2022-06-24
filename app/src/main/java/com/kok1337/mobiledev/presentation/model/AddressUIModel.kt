package com.kok1337.mobiledev.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.presentation.item.*

class AddressUIModel(
    private var _federalDistrictListSize: Int = 0,
    private var _subjectOfRusFedListSize: Int = 0,
    private var _forestryListSize: Int = 0,
    private var _localForestryListSize: Int = 0,
    private var _subForestryListSize: Int = 0,
    private var _areaListSize: Int = 0,
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

    val localForestryListObserver: (List<LocalForestryItem>) -> Unit = { localForestryListSize = it.size }
    var localForestryListSize: Int
        @Bindable get() = _localForestryListSize
        set(value) {
            _localForestryListSize = value
            notifyPropertyChanged(BR.localForestryListSize)
        }

    val subForestryListObserver: (List<SubForestryItem>) -> Unit = { subForestryListSize = it.size }
    var subForestryListSize: Int
        @Bindable get() = _subForestryListSize
        set(value) {
            _subForestryListSize = value
            notifyPropertyChanged(BR.subForestryListSize)
        }

    val areaListObserver: (List<AreaItem>) -> Unit = { areaListSize = it.size }
    var areaListSize: Int
        @Bindable get() = _areaListSize
        set(value) {
            _areaListSize = value
            notifyPropertyChanged(BR.areaListSize)
        }
}