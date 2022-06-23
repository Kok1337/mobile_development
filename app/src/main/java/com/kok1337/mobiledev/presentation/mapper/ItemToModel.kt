package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.ForestryItem
import com.kok1337.mobiledev.presentation.item.SubjectOfRusFedItem

fun FederalDistrictItem.toModel(): FederalDistrict = FederalDistrict(id, value)

fun SubjectOfRusFedItem.toModel(): SubjectOfRusFed = SubjectOfRusFed(id, value)

fun ForestryItem.toModel(): Forestry = Forestry(id, value)