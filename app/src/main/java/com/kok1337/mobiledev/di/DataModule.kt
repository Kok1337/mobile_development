package com.kok1337.mobiledev.di

import android.content.Context
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.database.dao.FederalDistrictDaoImpl
import com.kok1337.mobiledev.data.storage.DbFederalDistrictStorageImpl
import com.kok1337.mobiledev.data.repository.FederalDistrictRepoImpl
import com.kok1337.mobiledev.data.repository.WorkTypeRepoImpl
import com.kok1337.mobiledev.data.storage.FederalDistrictStorage
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo
import dagger.Module
import dagger.Provides
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.util.*
import javax.sql.DataSource

@Module
class DataModule {

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

    @Provides
    fun provideFederalDistrictDao(jdbcTemplate: JdbcTemplate): FederalDistrictDao {
        return FederalDistrictDaoImpl(
            jdbcTemplate = jdbcTemplate
        )
    }

    @Provides
    fun provideFederalDistrictStorage(federalDistrictDao: FederalDistrictDao): FederalDistrictStorage {
        return DbFederalDistrictStorageImpl(
            federalDistrictDao = federalDistrictDao
        )
    }


    @Provides
    fun provideFederalDistrictRepo(federalDistrictStorage: FederalDistrictStorage): FederalDistrictRepo {
        return FederalDistrictRepoImpl(
            federalDistrictStorage = federalDistrictStorage
        )
    }

    @Provides
    fun provideWorkTypeRepo(): WorkTypeRepo {
        return WorkTypeRepoImpl()
    }

}