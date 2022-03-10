package com.kok1337.mobiledev.di

import android.content.Context
import com.j256.ormlite.support.ConnectionSource
import com.kok1337.mobiledev.data.database.PostgresqlConnectionSource
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.database.storages.FederalDistrictStorageDbImpl
import com.kok1337.mobiledev.data.repository.FederalDistrictRepoImpl
import com.kok1337.mobiledev.data.repository.FederalDistrictStorage
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideConnectionSource(context: Context): ConnectionSource {
        return PostgresqlConnectionSource(
            context = context
        )
    }


    @Provides
    fun provideFederalDistrictDao(connectionSource: ConnectionSource): FederalDistrictDao {
        return FederalDistrictDao(
            connectionSource = connectionSource
        )
    }

    @Provides
    fun provideFederalDistrictStorage(federalDistrictDao: FederalDistrictDao): FederalDistrictStorage {
        return FederalDistrictStorageDbImpl(
            federalDistrictDao = federalDistrictDao
        )
    }

    @Provides
    fun provideFederalDistrictRepo(federalDistrictStorage: FederalDistrictStorage): FederalDistrictRepo {
        return FederalDistrictRepoImpl(
            federalDistrictStorage = federalDistrictStorage
        )
    }

}