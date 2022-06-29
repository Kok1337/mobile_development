package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.PlantingCharacteristicStorage
import com.kok1337.mobiledev.domain.model.PlantingCharacteristic
import com.kok1337.mobiledev.domain.repository.PlantingCharacteristicRepo
import java.util.*
import javax.inject.Inject

class PlantingCharacteristicRepoImpl @Inject constructor(
    private val plantingCharacteristicStorage: PlantingCharacteristicStorage
) : PlantingCharacteristicRepo {
    override suspend fun getPlantingCharacteristicByTaxId(taxId: UUID): PlantingCharacteristic {
        return plantingCharacteristicStorage.getPlantingCharacteristicByTaxId(taxId).toModel()
    }
}