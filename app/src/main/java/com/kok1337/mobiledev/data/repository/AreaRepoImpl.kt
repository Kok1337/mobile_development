package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.AreaStorage
import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.AreaParams
import com.kok1337.mobiledev.domain.repository.AreaRepo
import javax.inject.Inject

class AreaRepoImpl @Inject constructor(
    private val areaStorage: AreaStorage,
) : AreaRepo {
    override suspend fun getAllAreaByAreaParams(areaParams: AreaParams): List<Area> {
        return areaStorage.getAllByAreaParams(areaParams)
    }
}