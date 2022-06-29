package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.PlantingCharacteristic
import java.util.*

interface PlantingCharacteristicRepo {
    suspend fun getPlantingCharacteristicByTaxId(taxId: UUID): PlantingCharacteristic
}