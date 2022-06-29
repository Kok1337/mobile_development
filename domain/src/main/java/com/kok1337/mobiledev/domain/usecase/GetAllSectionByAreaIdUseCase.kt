package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.Section
import com.kok1337.mobiledev.domain.repository.SectionRepo
import javax.inject.Inject

class GetAllSectionByAreaIdUseCase @Inject constructor(
    private val sectionRepo: SectionRepo,
) {
    suspend fun invoke(area: Area): List<Section> = sectionRepo.getAllSectionByAreaId(area.id)
}