package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.TargetCategory

interface TargetCategoryRepo {
    suspend fun getAllTargetCategory(): List<TargetCategory>
    suspend fun checkIsProtectionTargetCategoryByTargetCategoryId(targetCategoryId: Int): Boolean
}