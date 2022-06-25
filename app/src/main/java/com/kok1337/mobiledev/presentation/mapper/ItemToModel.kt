package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.*
import com.kok1337.mobiledev.presentation.item.*

fun FederalDistrictItem.toModel(): FederalDistrict = FederalDistrict(id, value)

fun SubjectOfRusFedItem.toModel(): SubjectOfRusFed = SubjectOfRusFed(id, value)

fun ForestryItem.toModel(): Forestry = Forestry(id, value)

fun LocalForestryItem.toModel(): LocalForestry = LocalForestry(id, value)

fun SubForestryItem.toModel(): SubForestry = SubForestry(id, value)

fun AreaItem.toModel(): Area = Area(id, value, hasSection)

fun AreaParamsItem.toModel(): AreaParams = AreaParams(
    federalDistrictItem.toModel(),
    subjectOfRusFedItem.toModel(),
    forestryItem.toModel(),
    localForestryItem.toModel(),
    subForestryItem.toModel()
)

fun SectionItem.toModel(): Section = Section(name, s)

fun TaxSourceItem.toModel(): TaxSource = TaxSource(id, value)