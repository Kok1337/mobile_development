package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.LandCategory

interface LandCategoryRepo {
    suspend fun getAllLandCategory(): List<LandCategory>
}