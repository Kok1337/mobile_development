package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.ProtectionCategory
import com.kok1337.mobiledev.domain.model.TargetCategory

interface ProtectionCategoryRepo {
    suspend fun getAllProtectionCategoryByTargetCategoryId(targetCategoryId: Int): List<ProtectionCategory>
}