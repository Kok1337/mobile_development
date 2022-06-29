package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.ProtectionCategoryStorage
import com.kok1337.mobiledev.domain.model.ProtectionCategory
import com.kok1337.mobiledev.domain.repository.ProtectionCategoryRepo
import javax.inject.Inject

class ProtectionCategoryRepoImpl @Inject constructor(
    private val protectionCategoryStorage: ProtectionCategoryStorage,
) : ProtectionCategoryRepo {
    override suspend fun getAllProtectionCategoryByTargetCategoryId(targetCategoryId: Int): List<ProtectionCategory> {
        return protectionCategoryStorage.getAllByTargetCategoryId(targetCategoryId)
    }
}