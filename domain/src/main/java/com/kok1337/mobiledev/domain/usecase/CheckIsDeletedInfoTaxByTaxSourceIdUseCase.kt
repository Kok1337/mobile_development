package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.repository.InfoTaxRepo
import com.kok1337.mobiledev.domain.repository.TaxSourceRepo
import javax.inject.Inject

class CheckIsDeletedInfoTaxByTaxSourceIdUseCase @Inject constructor(
    private val infoTaxRepo: InfoTaxRepo
) {
    suspend fun invoke(taxSourceId: Int): Boolean = infoTaxRepo.checkIsDeletedInfoTaxByTaxSourceId(taxSourceId)
}