package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict

fun DictionaryEntity.toFederalDistrict(): FederalDistrict {
    return FederalDistrict(
        id = this.id!!,
        value = this.name!!
    )
}