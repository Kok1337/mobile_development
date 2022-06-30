package com.kok1337.mobiledev.presentation.util

import android.view.View
import androidx.databinding.BindingConversion
import com.kok1337.mobiledev.presentation.model.AddressUIModel

object DataBindingFunctions {
    @BindingConversion
    @JvmStatic fun getVisibility(enabled: Boolean): Int {
        return if (enabled) View.VISIBLE else View.GONE
    }
}