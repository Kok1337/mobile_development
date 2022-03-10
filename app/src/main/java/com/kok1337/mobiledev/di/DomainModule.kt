package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistricts
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideLoadAllFederalDistricts(federalDistrictRepo: FederalDistrictRepo): LoadAllFederalDistricts {
        return LoadAllFederalDistricts(
            federalDistrictRepo = federalDistrictRepo
        )
    }

}