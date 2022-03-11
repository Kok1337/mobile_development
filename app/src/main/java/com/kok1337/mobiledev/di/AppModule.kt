package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistrictsUseCase
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
    fun provideMainViewModelFactory(loadAllFederalDistrictsUseCase: LoadAllFederalDistrictsUseCase): MainViewModel.Factory {
        return MainViewModel.Factory(
            loadAllFederalDistrictsUseCase = loadAllFederalDistrictsUseCase
        )
    }

}