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