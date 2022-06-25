package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.TaxSource
import java.util.*

interface TaxSourceRepo {
    suspend fun getAllTaxSourceByAreaIdAndSectionName(
        areaId: UUID, sectionName: String
    ): List<TaxSource>
}