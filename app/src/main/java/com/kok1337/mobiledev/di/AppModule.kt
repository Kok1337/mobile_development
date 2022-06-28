package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictUseCase
import com.kok1337.mobiledev.domain.usecase.GetAllWorkTypesUseCase
import com.kok1337.mobiledev.domain.usecase.GetUserIdUseCase
import com.kok1337.mobiledev.domain.usecase.SaveUserIdUseCase
import com.kok1337.mobiledev.presentation.MainViewModel
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
    fun provideMainViewModelFactory(
        getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
        getUserIdUseCase: GetUserIdUseCase,
        saveUserIdUseCase: SaveUserIdUseCase,
    ): MainViewModel.Factory {
        return MainViewModel.Factory(
            getAllFederalDistrictUseCase = getAllFederalDistrictUseCase,
            getUserIdUseCase = getUserIdUseCase,
            saveUserIdUseCase = saveUserIdUseCase,
        )
    }

    @Provides
    fun provideWorkTypesViewModelFactory(
        getAllWorkTypesUseCase: GetAllWorkTypesUseCase
    ): WorkTypesViewModel.Factory {
        return WorkTypesViewModel.Factory(
            getAllWorkTypesUseCase = getAllWorkTypesUseCase
        )
    }

}