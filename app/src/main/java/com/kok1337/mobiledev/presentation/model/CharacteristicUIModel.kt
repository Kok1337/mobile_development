package com.kok1337.mobiledev.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class CharacteristicUIModel : BaseObservable() {

    val isLandCategorySpinnerEnabled: Boolean
        @Bindable get() = landCategoryListSize != 0 && isEdit

    val isTargetCategorySpinnerEnabled: Boolean
        @Bindable get() = targetCategoryListSize != 0 && isEdit

    val isProtectionCategorySpinnerEnabled: Boolean
        @Bindable get() = protectionCategoryListSize != 0 && isEdit

    val isProtectionTargetCategory: Boolean
        @Bindable get() = protectionCategoryListSize != 0

    val isOoptSpinnerEnabled: Boolean
        @Bindable get() = ooptListSize != 0 && isEdit

    val isOzuSpinnerEnabled: Boolean
        @Bindable get() = ozuListSize != 0 && isEdit

    val isBonitetSpinnerEnabled: Boolean
        @Bindable get() = bonitetListSize != 0 && isEdit

    val isTluSpinnerEnabled: Boolean
        @Bindable get() = tluListSize != 0 && isEdit

    val isOriginAndLandSpinnerEnabled: Boolean
        @Bindable get() = originListSize != 0 && isEdit


    var isEdit: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyChange()
        }

    var landCategoryListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.landCategorySpinnerEnabled)
        }

    var targetCategoryListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.targetCategorySpinnerEnabled)
        }

    var protectionCategoryListSize: Int = 0
        set(value) {
            field = value
            notifyChange()
        }

    var ooptListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.ooptSpinnerEnabled)
        }

    var ozuListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.ozuSpinnerEnabled)
        }

    var bonitetListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.bonitetSpinnerEnabled)
        }

    var tluListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.tluSpinnerEnabled)
        }

    var originListSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.originAndLandSpinnerEnabled)
        }
}