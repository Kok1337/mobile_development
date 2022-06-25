package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.data.database.dao.*
import com.kok1337.mobiledev.data.mapper.SubjectOfRusFedDao
import com.kok1337.mobiledev.data.mapper.SubjectOfRusFedDaoImpl
import com.kok1337.mobiledev.data.repository.LocalForestryRepoImpl
import com.kok1337.mobiledev.data.storage.*
import com.kok1337.mobiledev.domain.repository.LocalForestryRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.util.*
import javax.sql.DataSource

@Module(includes = [DatabaseModule::class, DataBindModule::class])
class DataModule

@Module
interface DataBindModule {

    @Binds @Suppress("FunctionName")
    fun bindWorkTypeStorageLocalImpl_to_WorkTypeStorage(workTypeStorageLocalImpl: WorkTypeStorageLocalImpl): WorkTypeStorage

    @Binds @Suppress("FunctionName")
    fun bindFederalDistrictDaoImpl_to_FederalDistrictDao(federalDistrictDaoImpl: FederalDistrictDaoImpl): FederalDistrictDao

    @Binds @Suppress("FunctionName")
    fun bindFederalDistrictStorageDbImpl_to_FederalDistrictStorage(federalDistrictStorageDbImpl: FederalDistrictStorageDbImpl): FederalDistrictStorage

    @Binds @Suppress("FunctionName")
    fun bindSubjectOfRusFedDaoImpl_to_SubjectOfRusFedDao(subjectOfRusFedDaoImpl: SubjectOfRusFedDaoImpl): SubjectOfRusFedDao

    @Binds @Suppress("FunctionName")
    fun bindSubjectOfRusFedStorageDbImpl_to_SubjectOfRusFedStorage(subjectOfRusFedStorageDbImpl: SubjectOfRusFedStorageDbImpl): SubjectOfRusFedStorage

    @Binds @Suppress("FunctionName")
    fun bindForestryDaoImpl_to_ForestryDao(forestryDaoImpl: ForestryDaoImpl): ForestryDao

    @Binds @Suppress("FunctionName")
    fun bindForestryStorageDbImpl_to_ForestryStorage(forestryStorageDbImpl: ForestryStorageDbImpl): ForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindLocalForestryDaoImpl_to_LocalForestryDao(localForestryDaoImpl: LocalForestryDaoImpl): LocalForestryDao

    @Binds @Suppress("FunctionName")
    fun bindLocalForestryStorageDbImpl_to_LocalForestryStorage(localForestryStorageDbImpl: LocalForestryStorageDbImpl): LocalForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindLocalForestryRepoImpl_to_LocalForestryRepo(localForestryRepoImpl: LocalForestryRepoImpl): LocalForestryRepo

    @Binds @Suppress("FunctionName")
    fun bindSubForestryDaoImpl_to_SubForestryDao(subForestryDaoImpl: SubForestryDaoImpl): SubForestryDao

    @Binds @Suppress("FunctionName")
    fun bindSubForestryStorageDbImpl_to_SubForestryStorage(subForestryStorageDbImpl: SubForestryStorageDbImpl): SubForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindAreaDaoImpl_to_AreaDao(areaDaoImpl: AreaDaoImpl): AreaDao

    @Binds @Suppress("FunctionName")
    fun bindAreaStorageDbImpl_to_AreaStorage(areaStorageDbImpl: AreaStorageDbImpl): AreaStorage

    @Binds @Suppress("FunctionName")
    fun bindSectionDaoImpl_to_SectionDao(sectionDaoImpl: SectionDaoImpl): SectionDao

    @Binds @Suppress("FunctionName")
    fun bindSectionStorageDbImpl_to_SectionStorage(sectionStorageDbImpl: SectionStorageDbImpl): SectionStorage

    @Binds @Suppress("FunctionName")
    fun bindTaxSourceDaoImpl_to_TaxSourceDao(taxSourceDaoImpl: TaxSourceDaoImpl): TaxSourceDao

    @Binds @Suppress("FunctionName")
    fun bindTaxSourceStorageDbImpl_to_TaxSourceStorage(taxSourceStorageDbImpl: TaxSourceStorageDbImpl): TaxSourceStorage
}

@Module
class DatabaseModule {
    companion object {
        private const val CONFIG_FILE_NAME = "config.properties"
    }

    @Provides
    fun provideDataSource(context: Context): DataSource {
        val properties = Properties()
        val inStream = context.assets.open(CONFIG_FILE_NAME)
        properties.load(inStream)

        val url = String.format(
            "jdbc:postgresql://%s:%s/%s",
            properties.getProperty("db_host"),
            properties.getProperty("db_port"),
            properties.getProperty("db_name")
        )
        val username = properties.getProperty("db_user")
        val password = properties.getProperty("db_password")
        val driverName = properties.getProperty("db_driver_name")

        val dataSource = DriverManagerDataSource(url, username, password)
        dataSource.setDriverClassName(driverName)
        return dataSource
    }

    @Provides
    fun provideJdbcTemplate(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}