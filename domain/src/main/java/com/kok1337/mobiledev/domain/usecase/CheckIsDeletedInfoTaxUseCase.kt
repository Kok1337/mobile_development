package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import javax.inject.Inject

class CheckIsDeletedInfoTaxUseCase @Inject constructor(
    private val taxSourceRepo: TaxSourceRepo
) {
    suspend fun invoke(taxSourceId: Int): Boolean = taxSourceRepo.isDeleted(taxSourceId)
}