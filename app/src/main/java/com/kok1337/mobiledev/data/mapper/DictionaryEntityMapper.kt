package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed

fun FederalDistrictEntity.toModel(): FederalDistrict {
    return FederalDistrict(
        id = this.id!!,
        value = this.name!!
    )
}

fun SubjectOfRusFedEntity.toModel(): SubjectOfRusFed {
    return SubjectOfRusFed(
        id = this.id!!,
        value = this.name!!
    )
}