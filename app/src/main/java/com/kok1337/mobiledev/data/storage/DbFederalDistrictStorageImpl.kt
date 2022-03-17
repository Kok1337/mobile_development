package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import com.kok1337.mobiledev.data.storage.FederalDistrictStorage

class DbFederalDistrictStorageImpl(
    private val federalDistrictDao: FederalDistrictDao,
) : FederalDistrictStorage {
    override fun getAllFederalDistricts(): List<FederalDistrictEntity> {
        return federalDistrictDao.findAll()
    }
}