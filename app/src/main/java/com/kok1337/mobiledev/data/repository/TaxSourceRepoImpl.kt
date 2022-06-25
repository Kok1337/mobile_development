package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.TaxSourceStorage
import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import java.util.*
import javax.inject.Inject

class TaxSourceRepoImpl @Inject constructor(
    private val taxSourceStorage: TaxSourceStorage,
) : TaxSourceRepo {
    override suspend fun getAllTaxSourceByAreaIdAndSectionName(areaId: UUID, sectionName: String): List<TaxSource> {
        return taxSourceStorage.getAllTaxSourceByAreaIdAndSectionName(areaId, sectionName).map { it.toModel() }
    }
}