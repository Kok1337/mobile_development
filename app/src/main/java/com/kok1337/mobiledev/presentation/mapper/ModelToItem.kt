package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.ForestryItem
import com.kok1337.mobiledev.presentation.item.SubjectOfRusFedItem
import com.kok1337.mobiledev.presentation.item.WorkTypeItem

fun WorkType.toItem(): WorkTypeItem = WorkTypeItem(work, name)

fun FederalDistrict.toItem(): FederalDistrictItem = FederalDistrictItem(id, value)

fun SubjectOfRusFed.toItem(): SubjectOfRusFedItem = SubjectOfRusFedItem(id, value)

fun Forestry.toItem(): ForestryItem = ForestryItem(id, value)