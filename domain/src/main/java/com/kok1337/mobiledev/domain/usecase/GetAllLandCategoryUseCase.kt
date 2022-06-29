package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.LandCategory
import com.kok1337.mobiledev.domain.repository.LandCategoryRepo
import javax.inject.Inject

class GetAllLandCategoryUseCase @Inject constructor(
    private val landCategoryRepo: LandCategoryRepo,
) {
    suspend fun invoke(): List<LandCategory> = landCategoryRepo.getAllLandCategory()
}