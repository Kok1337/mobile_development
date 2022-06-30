package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.*
import com.kok1337.mobiledev.presentation.item.*

fun WorkType.toItem(): WorkTypeItem = WorkTypeItem(work, name)

fun FederalDistrict.toItem(): FederalDistrictItem = FederalDistrictItem(id, value)

fun SubjectOfRusFed.toItem(): SubjectOfRusFedItem = SubjectOfRusFedItem(id, value)

fun Forestry.toItem(): ForestryItem = ForestryItem(id, value)

fun LocalForestry.toItem(): LocalForestryItem = LocalForestryItem(id, value)

fun SubForestry.toItem(): SubForestryItem = SubForestryItem(id, value)

fun Area.toItem(): AreaItem = AreaItem(id, value, hasSection)

fun Section.toItem(): SectionItem = SectionItem(name, s)

fun TaxSource.toItem(): TaxSourceItem = TaxSourceItem(id, value)

fun TaxYear.toItem(): TaxYearItem = TaxYearItem(taxId, year, draft)

fun LandCategory.toItem(): LandCategoryItem = LandCategoryItem(id, value)

fun TargetCategory.toItem(): TargetCategoryItem = TargetCategoryItem(id, value)

fun ProtectionCategory.toItem(): ProtectionCategoryItem = ProtectionCategoryItem(id, value)

fun Oopt.toItem(): OoptItem = OoptItem(id, value)

fun Ozu.toItem(): OzuItem = OzuItem(id, value)

fun Bonitet.toItem(): BonitetItem = BonitetItem(id, value)

fun Tlu.toItem(): TluItem = TluItem(id, value)

fun Origin.toItem(): OriginItem = OriginItem(id, name, value)