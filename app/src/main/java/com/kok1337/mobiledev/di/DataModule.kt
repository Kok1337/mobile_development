package com.kok1337.mobiledev.di

import android.content.Context
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
    fun bindFederalDistrictStorageDbImpl_to_FederalDistrictStorage(federalDistrictStorageDbImpl: FederalDistrictStorageDbImpl): FederalDistrictStorage

    @Binds @Suppress("FunctionName")
    fun bindSubjectOfRusFedStorageDbImpl_to_SubjectOfRusFedStorage(subjectOfRusFedStorageDbImpl: SubjectOfRusFedStorageDbImpl): SubjectOfRusFedStorage

    @Binds @Suppress("FunctionName")
    fun bindForestryStorageDbImpl_to_ForestryStorage(forestryStorageDbImpl: ForestryStorageDbImpl): ForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindLocalForestryStorageDbImpl_to_LocalForestryStorage(localForestryStorageDbImpl: LocalForestryStorageDbImpl): LocalForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindLocalForestryRepoImpl_to_LocalForestryRepo(localForestryRepoImpl: LocalForestryRepoImpl): LocalForestryRepo

    @Binds @Suppress("FunctionName")
    fun bindSubForestryStorageDbImpl_to_SubForestryStorage(subForestryStorageDbImpl: SubForestryStorageDbImpl): SubForestryStorage

    @Binds @Suppress("FunctionName")
    fun bindAreaStorageDbImpl_to_AreaStorage(areaStorageDbImpl: AreaStorageDbImpl): AreaStorage

    @Binds @Suppress("FunctionName")
    fun bindSectionStorageDbImpl_to_SectionStorage(sectionStorageDbImpl: SectionStorageDbImpl): SectionStorage

    @Binds @Suppress("FunctionName")
    fun bindTaxSourceStorageDbImpl_to_TaxSourceStorage(taxSourceStorageDbImpl: TaxSourceStorageDbImpl): TaxSourceStorage

    @Binds @Suppress("FunctionName")
    fun bindTaxYearStorageDbImpl_to_TaxYearStorage(taxYearStorageDbImpl: TaxYearStorageDbImpl): TaxYearStorage

    @Binds @Suppress("FunctionName")
    fun bindCacheStorageShPrImpl_to_CacheStorage(cacheStorageShPrImpl: CacheStorageShPrImpl): CacheStorage

    @Binds @Suppress("FunctionName")
    fun bindInfoTaxStorageDbImpl_to_InfoTaxStorage(infoTaxStorageDbImpl: InfoTaxStorageDbImpl): InfoTaxStorage

    @Binds @Suppress("FunctionName")
    fun bindPlantingCharacteristicStorageDbImpl_to_PlantingCharacteristicStorage(plantingCharacteristicStorageDbImpl: PlantingCharacteristicStorageDbImpl): PlantingCharacteristicStorage

    @Binds @Suppress("FunctionName")
    fun bindLandCategoryStorageDbImpl_to_LandCategoryStorage(landCategoryStorageDbImpl: LandCategoryStorageDbImpl): LandCategoryStorage

    @Binds @Suppress("FunctionName")
    fun bindProtectionCategoryStorageDbImpl_to_ProtectionCategoryStorage(protectionCategoryStorageDbImpl: ProtectionCategoryStorageDbImpl): ProtectionCategoryStorage

    @Binds @Suppress("FunctionName")
    fun bindTargetCategoryStorageDbImpl_to_TargetCategoryStorage(targetCategoryStorageDbImpl: TargetCategoryStorageDbImpl): TargetCategoryStorage

    @Binds @Suppress("FunctionName")
    fun bindOoptStorageDbImpl_to_OoptStorage(ooptStorageDbImpl: OoptStorageDbImpl): OoptStorage

    @Binds @Suppress("FunctionName")
    fun bindOzuStorageDbImpl_to_OzuStorage(ozuStorageDbImpl: OzuStorageDbImpl): OzuStorage

    @Binds @Suppress("FunctionName")
    fun bindBonitetStorageDbImpl_to_BonitetStorage(bonitetStorageDbImpl: BonitetStorageDbImpl): BonitetStorage

    @Binds @Suppress("FunctionName")
    fun bindTluStorageDbImpl_to_TluStorage(tluStorageDbImpl: TluStorageDbImpl): TluStorage

    @Binds @Suppress("FunctionName")
    fun bindOriginStorageDbImpl_to_OriginStorage(originStorageDbImpl: OriginStorageDbImpl): OriginStorage

    @Binds @Suppress("FunctionName")
    fun bindLandStorageLocalImpl_to_LandStorage(landStorageLocalImpl: LandStorageLocalImpl): LandStorage
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