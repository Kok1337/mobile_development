package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Land
import com.kok1337.mobiledev.domain.repository.LandRepo
import javax.inject.Inject

class GetAllLandUseCase @Inject constructor(
    private val landRepo: LandRepo,
) {
    suspend fun invoke(): List<Land> = landRepo.getAllLand()
}