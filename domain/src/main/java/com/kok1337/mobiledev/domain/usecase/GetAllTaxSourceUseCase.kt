package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.Section
import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import javax.inject.Inject

class GetAllTaxSourceUseCase @Inject constructor(
    private val taxSourceRepo: TaxSourceRepo,
) {
    suspend fun invoke(area: Area, section: Section): List<TaxSource> =
        taxSourceRepo.getAllTaxSourceByAreaIdAndSectionName(area.id, section.name)
}