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
    private var _sectionListSize: Int = 0,
    private var _taxSourceListSize: Int = 0,
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

    val sectionListObserver: (List<SectionItem>) -> Unit = { sectionListSize = it.size }
    var sectionListSize: Int
        @Bindable get() = _sectionListSize
        set(value) {
            _sectionListSize = value
            notifyPropertyChanged(BR.sectionListSize)
        }

    val taxSourceListObserver: (List<TaxSourceItem>) -> Unit = { taxSourceListSize = it.size }
    var taxSourceListSize: Int
        @Bindable get() = _taxSourceListSize
        set(value) {
            _taxSourceListSize = value
            notifyPropertyChanged(BR.taxSourceListSize)
        }
}