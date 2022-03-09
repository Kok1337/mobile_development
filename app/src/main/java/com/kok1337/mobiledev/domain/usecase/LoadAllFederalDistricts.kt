package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo

class LoadAllFederalDistricts(
    private val federalDistrictRepo: FederalDistrictRepo
    ) {

    suspend fun invoke(): List<FederalDistrict> = federalDistrictRepo.getAllFederalDistricts()
}