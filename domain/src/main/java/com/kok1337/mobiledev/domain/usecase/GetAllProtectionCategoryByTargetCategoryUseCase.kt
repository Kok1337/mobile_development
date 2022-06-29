package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.ProtectionCategory
import com.kok1337.mobiledev.domain.model.TargetCategory
import com.kok1337.mobiledev.domain.repository.ProtectionCategoryRepo
import javax.inject.Inject

class GetAllProtectionCategoryByTargetCategoryUseCase @Inject constructor(
    private val protectionCategoryRepo: ProtectionCategoryRepo,
) {
    suspend fun invoke(targetCategory: TargetCategory): List<ProtectionCategory> =
        protectionCategoryRepo.getAllProtectionCategoryByTargetCategoryId(targetCategory.id)
}