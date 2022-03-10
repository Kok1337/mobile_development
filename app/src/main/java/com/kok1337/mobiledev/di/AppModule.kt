package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistricts
import com.kok1337.mobiledev.presentation.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(loadAllFederalDistricts: LoadAllFederalDistricts): MainViewModel.Factory {
        return MainViewModel.Factory(
            loadAllFederalDistricts = loadAllFederalDistricts
        )
    }

}