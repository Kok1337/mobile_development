package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import javax.inject.Inject

class GetAllTaxSourceByTaxSourceParamsUseCase @Inject constructor(
    private val taxSourceRepo: TaxSourceRepo,
) {
    suspend fun invoke(taxSourceParams: TaxSourceParams): List<TaxSource> =
        taxSourceRepo.getAllTaxSourceByTaxSourceParams(taxSourceParams)
}