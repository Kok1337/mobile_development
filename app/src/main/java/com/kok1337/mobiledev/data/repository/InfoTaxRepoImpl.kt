package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.InfoTaxStorage
import com.kok1337.mobiledev.domain.model.InfoTaxParams
import com.kok1337.mobiledev.domain.model.InfoTaxSaveParams
import com.kok1337.mobiledev.domain.repository.InfoTaxRepo
import java.util.*
import javax.inject.Inject

class InfoTaxRepoImpl @Inject constructor(
    private val infoTaxStorage: InfoTaxStorage,
) : InfoTaxRepo {
    override suspend fun checkIsExistInfoTax(infoTaxParams: InfoTaxParams): Boolean {
        return infoTaxStorage.checkIsExistsInfoTax(infoTaxParams)
    }

    override suspend fun saveInfoTax(infoTaxSaveParams: InfoTaxSaveParams, userId: Int) {
        infoTaxStorage.saveInfoTax(infoTaxSaveParams, userId)
    }

    override suspend fun deleteInfoTax(infoTaxId: UUID) {
        infoTaxStorage.deleteInfoTax(infoTaxId)
    }
}