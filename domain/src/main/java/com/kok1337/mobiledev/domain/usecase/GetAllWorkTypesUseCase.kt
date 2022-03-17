package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo

class GetAllWorkTypesUseCase(
    private val workTypeRepo: WorkTypeRepo,
) {
    suspend fun invoke(): List<WorkType> = workTypeRepo.getAllWorkTypes()
}