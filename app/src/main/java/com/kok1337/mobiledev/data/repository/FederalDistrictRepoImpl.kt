package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.FederalDistrictEntityMapper
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo

class FederalDistrictRepoImpl(private val federalDistrictStorage: FederalDistrictStorage) : FederalDistrictRepo {
    override suspend fun getAllFederalDistricts(): List<FederalDistrict> {
        return federalDistrictStorage.getAllFederalDistricts().map { FederalDistrictEntityMapper().toFederalDistrict(it) }
    }
}