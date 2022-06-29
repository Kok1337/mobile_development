package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CharacteristicViewModel : ViewModel(

) {


    class Factory @Inject constructor(

    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacteristicViewModel(

            ) as T
        }
    }
}