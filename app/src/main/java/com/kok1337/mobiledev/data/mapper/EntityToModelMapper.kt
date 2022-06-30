package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.entity.*
import com.kok1337.mobiledev.domain.model.*

fun DictionaryEntity.toFederalDistrictModel(): FederalDistrict = FederalDistrict(this.id!!, this.name!!)

fun DictionaryEntity.toSubjectOfRusFedModel(): SubjectOfRusFed = SubjectOfRusFed(this.id!!, this.name!!)

fun DictionaryEntity.toForestryModel(): Forestry = Forestry(this.id!!, this.name!!)

fun DictionaryEntity.toLocalForestryModel(): LocalForestry = LocalForestry(this.id!!, this.name!!)

fun DictionaryEntity.toSubForestryModel(): SubForestry = SubForestry(this.id ?: 0, this.name ?: "(нет)")

fun AreaEntity.toModel(): Area = Area(this.id!!, this.name!!, this.hasSection!!)

fun SectionEntity.toModel(): Section = Section(this.name!!, this.s ?: 0.0)

fun TaxSourceEntity.toModel(): TaxSource = TaxSource(this.id!!, this.name!!)

fun DictionaryEntity.toTaxSourceModel(): TaxSource = TaxSource(this.id!!, this.name!!)

fun TaxYearEntity.toModel(): TaxYear = TaxYear(this.taxId!!, this.year ?: 0, this.draft ?: true)

fun PlantingCharacteristicEntity.toModel(): PlantingCharacteristic = PlantingCharacteristic(
    id!!, s!!, landCatId, forestPurposeId, protectionCategoryId, ooptId, oopt ?: " ",
    ozuId, ozu ?: "", bonitetId, forestType ?: "", tluId, isNatural,
    nonForestLandId, unforestedLandId, stockDead, stockOpenStand, stockSingleTree,
    stockFellingDebris, stockLiquidDebris, stockSection
)

fun DictionaryEntity.toLandCategoryModel(): LandCategory = LandCategory(this.id!!, this.name!!)

fun DictionaryEntity.toProtectionCategoryModel(): ProtectionCategory = ProtectionCategory(this.id!!, this.name!!)

fun DictionaryEntity.toTargetCategoryModel(): TargetCategory = TargetCategory(this.id!!, this.name!!)

fun DictionaryEntity.toOoptModel(): Oopt = Oopt(this.id!!, this.name!!)

fun DictionaryEntity.toOzuModel(): Ozu = Ozu(this.id!!, this.name!!)

fun DictionaryEntity.toBonitetModel(): Bonitet = Bonitet(this.id!!, this.name!!)

fun DictionaryEntity.toTluModel(): Tlu = Tlu(this.id!!, this.name!!)

fun OriginEntity.toModel(): Origin = Origin(this.id!!, this.name!!, this.value)