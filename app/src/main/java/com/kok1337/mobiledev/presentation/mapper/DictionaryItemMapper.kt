package com.kok1337.mobiledev.presentation.mapper

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.presentation.item.DictionaryItem

fun FederalDistrict.toDictionaryItem(): DictionaryItem {
    return DictionaryItem(
        id = this.id,
        name = this.value
    )
}