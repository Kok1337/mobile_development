package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.TaxSourceStorage
import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import javax.inject.Inject

class TaxSourceRepoImpl @Inject constructor(
    private val taxSourceStorage: TaxSourceStorage,
) : TaxSourceRepo {
    override suspend fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSource> {
        return taxSourceStorage.getAllTaxSourceByTaxSourceParams(taxSourceParams).map { it.toModel() }
    }
}