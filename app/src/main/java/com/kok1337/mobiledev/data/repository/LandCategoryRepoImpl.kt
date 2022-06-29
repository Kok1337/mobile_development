package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.LandCategoryStorage
import com.kok1337.mobiledev.domain.model.LandCategory
import com.kok1337.mobiledev.domain.repository.LandCategoryRepo
import javax.inject.Inject

class LandCategoryRepoImpl @Inject constructor(
    private val landCategoryStorage: LandCategoryStorage,
) : LandCategoryRepo {
    override suspend fun getAllLandCategory(): List<LandCategory> {
        return landCategoryStorage.getAllLandCategory().map { it.toModel() }
    }
}