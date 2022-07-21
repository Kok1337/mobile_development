package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.NonForestLandCategory
import com.kok1337.mobiledev.domain.repository.NonForestLandCategoryRepo
import javax.inject.Inject

class GetAllNonForestLandCategoryUseCase @Inject constructor(
    private val nonForestLandCategoryRepo: NonForestLandCategoryRepo,
) {
    suspend fun invoke(): List<NonForestLandCategory> =
        nonForestLandCategoryRepo.getAllNonForestLandCategory()
}