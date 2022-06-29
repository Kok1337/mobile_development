package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.repository.InfoTaxRepo
import java.util.*
import javax.inject.Inject

class DeleteInfoTaxUseCase @Inject constructor(
    private val infoTaxRepo: InfoTaxRepo
) {
    suspend fun invoke(infoTaxId: UUID) = infoTaxRepo.deleteInfoTax(infoTaxId)
}