package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.PlantingCharacteristic
import com.kok1337.mobiledev.domain.repository.PlantingCharacteristicRepo
import java.util.*
import javax.inject.Inject

class GetPlantingCharacteristicByTaxIdUseCase @Inject constructor(
    private val plantingCharacteristicRepo: PlantingCharacteristicRepo
) {
    suspend fun getPlantingCharacteristicByTaxId(taxId: UUID): PlantingCharacteristic =
        plantingCharacteristicRepo.getPlantingCharacteristicByTaxId(taxId)
}