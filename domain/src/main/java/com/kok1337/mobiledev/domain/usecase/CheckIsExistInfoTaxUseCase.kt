package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.InfoTaxParams
import com.kok1337.mobiledev.domain.repository.InfoTaxRepo
import javax.inject.Inject

class CheckIsExistInfoTaxUseCase @Inject constructor(
    private val infoTaxRepo: InfoTaxRepo
) {
    suspend fun invoke(infoTaxParams: InfoTaxParams): Boolean =
        infoTaxRepo.checkIsExistInfoTax(infoTaxParams)
}