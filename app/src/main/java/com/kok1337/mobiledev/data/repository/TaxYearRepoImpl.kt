package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.TaxYearStorage
import com.kok1337.mobiledev.domain.model.TaxYear
import com.kok1337.mobiledev.domain.model.TaxYearParams
import com.kok1337.mobiledev.domain.repository.TaxYearRepo
import javax.inject.Inject

class TaxYearRepoImpl @Inject constructor(
    private val taxYearStorage: TaxYearStorage,
) : TaxYearRepo {
    override suspend fun getAllTaxYearByTaxYearParams(taxYearParams: TaxYearParams): List<TaxYear> {
        return taxYearStorage.getAllByTaxYearParams(taxYearParams)
    }
}