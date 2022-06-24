package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.*
import com.kok1337.mobiledev.domain.model.*

fun FederalDistrictEntity.toModel(): FederalDistrict = FederalDistrict(this.id!!, this.name!!)

fun SubjectOfRusFedEntity.toModel(): SubjectOfRusFed = SubjectOfRusFed(this.id!!, this.name!!)

fun ForestryEntity.toModel(): Forestry = Forestry(this.id!!, this.name!!)

fun LocalForestryEntity.toModel(): LocalForestry = LocalForestry(this.id!!, this.name!!)

fun SubForestryEntity.toModel(): SubForestry = SubForestry(this.id ?: 0, this.name ?: "(нет)")

fun AreaEntity.toModel(): Area = Area(this.id!!, this.name!!, this.hasSection!!)