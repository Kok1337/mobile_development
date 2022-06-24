package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.AreaParams
import com.kok1337.mobiledev.domain.repository.AreaRepo
import javax.inject.Inject

class GetAllAreaUseCase @Inject constructor(
    private val areaRepo: AreaRepo,
) {
    suspend fun invoke(areaParams: AreaParams): List<Area> = areaRepo.getAllAreaByAreaParams(areaParams)
}