package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import javax.inject.Inject

class GetAllFederalDistrictUseCase @Inject constructor(
    private val federalDistrictRepo: FederalDistrictRepo,
) {
    suspend fun invoke(): List<FederalDistrict> = federalDistrictRepo.getAllFederalDistrict()
}