package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.presentation.item.WorkTypeItem

fun WorkType.toWorkTypeItem(): WorkTypeItem {
    return WorkTypeItem(
        work = work,
        name = name
    )
}