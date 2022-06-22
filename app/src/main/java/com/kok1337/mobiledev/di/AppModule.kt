package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictUseCase
import com.kok1337.mobiledev.domain.usecase.GetAllWorkTypesUseCase
import com.kok1337.mobiledev.presentation.MainViewModel
import com.kok1337.mobiledev.presentation.fragment.taxation.AddressViewModel
import com.kok1337.mobiledev.presentation.fragment.toolbar.WorkTypesViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase): MainViewModel.Factory {
        return MainViewModel.Factory(
            getAllFederalDistrictUseCase = getAllFederalDistrictUseCase
        )
    }

    @Provides
    fun provideWorkTypesViewModelFactory(getAllWorkTypesUseCase: GetAllWorkTypesUseCase): WorkTypesViewModel.Factory {
        return WorkTypesViewModel.Factory(
            getAllWorkTypesUseCase = getAllWorkTypesUseCase
        )
    }

    @Provides
    fun provideAddressViewModelFactory(getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase): AddressViewModel.Factory {
        return AddressViewModel.Factory(
            getAllFederalDistrictUseCase = getAllFederalDistrictUseCase
        )
    }

}