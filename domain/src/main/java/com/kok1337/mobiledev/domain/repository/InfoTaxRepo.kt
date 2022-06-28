package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.InfoTaxParams
import com.kok1337.mobiledev.domain.model.InfoTaxSaveParams

interface InfoTaxRepo {
    suspend fun checkInfoTax(infoTaxParams: InfoTaxParams): Boolean
    suspend fun saveInfoTax(infoTaxSaveParams: InfoTaxSaveParams, userId: Int)
}