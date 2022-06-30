package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.TargetCategory
import com.kok1337.mobiledev.domain.repository.TargetCategoryRepo
import javax.inject.Inject

class CheckIsProtectionTargetCategoryByTargetCategoryIdUseCase @Inject constructor(
    private val targetCategoryRepo: TargetCategoryRepo,
) {
    suspend fun invoke(targetCategory: TargetCategory): Boolean =
        targetCategoryRepo.checkIsProtectionTargetCategoryByTargetCategoryId(targetCategory.id)
}