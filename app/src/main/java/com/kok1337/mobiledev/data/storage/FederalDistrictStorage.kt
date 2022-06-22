package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.FederalDistrictDao
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import javax.inject.Inject

interface FederalDistrictStorage {
    fun getAllFederalDistrict(): List<FederalDistrictEntity>
}

class FederalDistrictStorageDbImpl @Inject constructor(
    private val federalDistrictDao: FederalDistrictDao,
) : FederalDistrictStorage {
    override fun getAllFederalDistrict(): List<FederalDistrictEntity> {
        return federalDistrictDao.findAll()
    }
}