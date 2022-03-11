package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict

fun FederalDistrictEntity.toFederalDistrict(): FederalDistrict {
    return FederalDistrict(
        id = this.id!!,
        value = this.name!!
    )
}