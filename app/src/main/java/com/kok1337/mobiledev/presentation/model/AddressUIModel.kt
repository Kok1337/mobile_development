package com.kok1337.mobiledev.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class AddressUIModel : BaseObservable() {

    val isFederalDistrictSpinnerEnabled: Boolean
        @Bindable get() = federalDistrictListSize != 0

    val isSubjectOfRusFedSpinnerEnabled: Boolean
        @Bindable get() = subjectOfRusFedListSize != 0

    val isForestrySpinnerEnabled: Boolean
        @Bindable get() = forestryListSize != 0

    val isLocalForestrySpinnerEnabled: Boolean
        @Bindable get() = localForestryListSize != 0

    val isSubForestrySpinnerEnabled: Boolean
        @Bindable get() = subForestryListSize != 0

    val isAreaSpinnerEnabled: Boolean
        @Bindable get() = areaListSize != 0

    val isSectionSpinnerEnabled: Boolean
        @Bindable get() = sectionListSize != 0

    val isTaxSourceSpinnerEnabled: Boolean
        @Bindable get() = taxSourceListSize != 0

    val isTaxYearSpinnerEnabled: Boolean
        @Bindable get() = taxYearListSize != 0

    val isDelTaxButtonEnabled: Boolean
        @Bindable get() = isSelectedTaxNotNull && isCanBeDeleted && isEdit

    val isAddTaxButtonEnabled: Boolean
        @Bindable get() = isSelectedAreaNotNull && isEdit


    var isEdit: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }

    var federalDistrictListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.federalDistrictSpinnerEnabled)
        }

    var subjectOfRusFedListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.subjectOfRusFedSpinnerEnabled)
        }

    var forestryListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.forestrySpinnerEnabled)
        }

    var localForestryListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.localForestrySpinnerEnabled)
        }

    var subForestryListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.subForestrySpinnerEnabled)
        }

    var areaListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.areaSpinnerEnabled)
        }

    var sectionListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.sectionSpinnerEnabled)
        }

    var taxSourceListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.taxSourceSpinnerEnabled)
        }

    var taxYearListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.taxYearSpinnerEnabled)
        }

    var isSelectedTaxNotNull: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }

    var isSelectedAreaNotNull: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }

    var isCanBeDeleted: Boolean = false
        set(value) {
            field = value
            notifyChange()
        }
}