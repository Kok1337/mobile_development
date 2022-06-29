package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.*
import com.kok1337.mobiledev.domain.model.*

fun FederalDistrictEntity.toModel(): FederalDistrict = FederalDistrict(this.id!!, this.name!!)

fun SubjectOfRusFedEntity.toModel(): SubjectOfRusFed = SubjectOfRusFed(this.id!!, this.name!!)

fun ForestryEntity.toModel(): Forestry = Forestry(this.id!!, this.name!!)

fun LocalForestryEntity.toModel(): LocalForestry = LocalForestry(this.id!!, this.name!!)

fun SubForestryEntity.toModel(): SubForestry = SubForestry(this.id ?: 0, this.name ?: "(нет)")

fun AreaEntity.toModel(): Area = Area(this.id!!, this.name!!, this.hasSection!!)

fun SectionEntity.toModel(): Section = Section(this.name!!, this.s ?: 0.0)

fun TaxSourceEntity.toModel(): TaxSource = TaxSource(this.id!!, this.name!!)

fun TaxYearEntity.toModel(): TaxYear = TaxYear(this.taxId!!, this.year ?: 0, this.draft ?: true)

fun PlantingCharacteristicEntity.toModel(): PlantingCharacteristic = PlantingCharacteristic(
    id!!, s!!, landCatId, forestPurposeId, protectionCategoryId, ooptId, oopt ?: " ",
    ozuId, ozu ?: "", bonitetId, forestType ?: "", tluId, isNatural,
    nonForestLandId, unforestedLandId, stockDead, stockOpenStand, stockSingleTree,
    stockFellingDebris, stockLiquidDebris, stockSection
)