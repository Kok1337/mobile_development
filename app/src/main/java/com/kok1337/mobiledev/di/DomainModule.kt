package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistricts
import org.koin.dsl.module

val domainModule = module {
    factory<LoadAllFederalDistricts> {
        LoadAllFederalDistricts(federalDistrictRepo = get())
    }
}