package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDaoImpl
import com.kok1337.mobiledev.data.database.dao.SubjectOfRusFedDao
import com.kok1337.mobiledev.data.database.dao.SubjectOfRusFedDaoImpl
import com.kok1337.mobiledev.data.storage.*
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