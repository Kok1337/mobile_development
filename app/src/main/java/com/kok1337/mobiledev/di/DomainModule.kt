package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictsUseCase
import com.kok1337.mobiledev.domain.usecase.GetAllWorkTypesUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetAllFederalDistricts(federalDistrictRepo: FederalDistrictRepo): GetAllFederalDistrictsUseCase {
        return GetAllFederalDistrictsUseCase(
            federalDistrictRepo = federalDistrictRepo
        )
    }

    @Provides
    fun provideGetAll(workTypeRepo: WorkTypeRepo): GetAllWorkTypesUseCase {
        return GetAllWorkTypesUseCase(
            workTypeRepo = workTypeRepo
        )
    }

}