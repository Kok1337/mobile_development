package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.TaxYear
import com.kok1337.mobiledev.domain.model.TaxYearParams

interface TaxYearRepo {
    suspend fun getAllTaxYearByTaxYearParams(taxYearParams: TaxYearParams): List<TaxYear>
}