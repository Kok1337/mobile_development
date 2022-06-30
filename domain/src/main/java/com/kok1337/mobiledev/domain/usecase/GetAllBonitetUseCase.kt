package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Bonitet
import com.kok1337.mobiledev.domain.repository.BonitetRepo
import javax.inject.Inject

class GetAllBonitetUseCase @Inject constructor(
    private val bonitetRepo: BonitetRepo,
) {
    suspend fun invoke(): List<Bonitet> = bonitetRepo.getAllBonitet()
}
