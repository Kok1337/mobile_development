package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.data.database.PostgresqlConnectionSource
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.database.storages.FederalDistrictStorageDbImpl
import com.kok1337.mobiledev.data.repository.FederalDistrictRepoImpl
import com.kok1337.mobiledev.data.repository.FederalDistrictStorage
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import org.koin.dsl.module

val dataModule = module {
    single<PostgresqlConnectionSource> {
        PostgresqlConnectionSource(context = get())
    }

    single<FederalDistrictDao> {
        FederalDistrictDao(connectionSource = get())
    }
    single<FederalDistrictStorage> {
        FederalDistrictStorageDbImpl(federalDistrictDao = get())
    }
    single<FederalDistrictRepo> {
        FederalDistrictRepoImpl(federalDistrictStorage = get())
    }
}