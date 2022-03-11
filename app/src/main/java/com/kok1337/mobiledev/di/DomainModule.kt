package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistrictsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideLoadAllFederalDistricts(federalDistrictRepo: FederalDistrictRepo): LoadAllFederalDistrictsUseCase {
        return LoadAllFederalDistrictsUseCase(
            federalDistrictRepo = federalDistrictRepo
        )
    }

}