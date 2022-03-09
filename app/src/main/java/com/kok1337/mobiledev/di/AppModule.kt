package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(loadAllFederalDistricts = get())
    }
}