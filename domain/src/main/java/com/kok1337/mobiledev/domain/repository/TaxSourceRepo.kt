package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.model.TaxSourceParams

interface TaxSourceRepo {
    suspend fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSource>
}