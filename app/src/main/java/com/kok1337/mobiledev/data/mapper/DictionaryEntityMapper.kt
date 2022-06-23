package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import com.kok1337.mobiledev.data.entity.ForestryEntity
import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed

fun FederalDistrictEntity.toModel(): FederalDistrict = FederalDistrict(
    id = this.id!!,
    value = this.name!!
)

fun SubjectOfRusFedEntity.toModel(): SubjectOfRusFed = SubjectOfRusFed(
    id = this.id!!,
    value = this.name!!
)

fun ForestryEntity.toModel(): Forestry = Forestry(
    id = this.id!!,
    value = this.name!!
)