package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.FederalDistrictStorage
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo

class FederalDistrictRepoImpl(
    private val federalDistrictStorage: FederalDistrictStorage,
) : FederalDistrictRepo {
    override suspend fun getAllFederalDistrict(): List<FederalDistrict> {
        return federalDistrictStorage.getAllFederalDistrict().map { it.toModel() }
    }
}