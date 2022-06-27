package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.TaxYear
import com.kok1337.mobiledev.domain.model.TaxYearParams
import com.kok1337.mobiledev.domain.repository.TaxYearRepo
import javax.inject.Inject

class GetAllTaxYearByTaxYearParamsUseCase @Inject constructor(
    private val taxYearRepo: TaxYearRepo,
) {
    suspend fun invoke(taxYearParams: TaxYearParams): List<TaxYear> =
        taxYearRepo.getAllTaxYearByTaxYearParams(taxYearParams)
}
