package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.model.FederalDistrictEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict

class FederalDistrictEntityMapper {
    fun toFederalDistrict(federalDistrictEntity: FederalDistrictEntity): FederalDistrict {
        return FederalDistrict(
            id = federalDistrictEntity.id!!,
            value = federalDistrictEntity.value!!
        )
    }
}