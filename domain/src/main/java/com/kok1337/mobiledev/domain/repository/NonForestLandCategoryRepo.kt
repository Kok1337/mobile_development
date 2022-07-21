package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.NonForestLandCategory

interface NonForestLandCategoryRepo {
    suspend fun getAllNonForestLandCategory(): List<NonForestLandCategory>
}