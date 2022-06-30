package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.TargetCategoryStorage
import com.kok1337.mobiledev.domain.model.TargetCategory
import com.kok1337.mobiledev.domain.repository.TargetCategoryRepo
import javax.inject.Inject

class TargetCategoryRepoImpl @Inject constructor(
    private val targetCategoryStorage: TargetCategoryStorage,
) : TargetCategoryRepo {
    override suspend fun getAllTargetCategory(): List<TargetCategory> {
        return targetCategoryStorage.getAll()
    }

    override suspend fun checkIsProtectionTargetCategoryByTargetCategoryId(targetCategoryId: Int): Boolean {
        return targetCategoryStorage.isProtectionByTargetCategoryId(targetCategoryId)
    }
}
