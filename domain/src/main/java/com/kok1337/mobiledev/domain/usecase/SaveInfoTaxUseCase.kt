package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.InfoTaxSaveParams
import com.kok1337.mobiledev.domain.repository.CacheRepo
import com.kok1337.mobiledev.domain.repository.InfoTaxRepo
import javax.inject.Inject

class SaveInfoTaxUseCase @Inject constructor(
    private val infoTaxRepo: InfoTaxRepo,
    private val cacheRepo: CacheRepo
) {
    suspend fun invoke(infoTaxSaveParams: InfoTaxSaveParams) {
        val userId = cacheRepo.getUserId()
        if (userId == -1) throw IllegalStateException()
        infoTaxRepo.saveInfoTax(infoTaxSaveParams, userId)
    }
}