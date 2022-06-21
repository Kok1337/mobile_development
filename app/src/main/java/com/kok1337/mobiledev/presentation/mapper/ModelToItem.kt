package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.WorkTypeItem

fun WorkType.toWorkTypeItem(): WorkTypeItem {
    return WorkTypeItem(
        work = work,
        name = name
    )
}

fun FederalDistrict.toItem(): FederalDistrictItem {
    return FederalDistrictItem(id, value)
}