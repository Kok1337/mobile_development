package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.AreaParams

interface AreaRepo {
    suspend fun getAllAreaByAreaParams(areaParams: AreaParams): List<Area>
}