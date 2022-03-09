package com.kok1337.mobiledev.data.database.storages

import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.model.FederalDistrictEntity
import com.kok1337.mobiledev.data.repository.FederalDistrictStorage

class FederalDistrictStorageDbImpl(private val federalDistrictDao: FederalDistrictDao) :
    FederalDistrictStorage {
    override suspend fun getAllFederalDistricts(): List<FederalDistrictEntity> {
        return federalDistrictDao.queryForAll()
    }
}