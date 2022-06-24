package com.kok1337.mobiledev.presentation.view.searchablespinner

import com.kok1337.mobiledev.R

class SortType<T>(
    val type: Type,
    val comparator: Comparator<T>,
) {
    val iconResource = type.iconResource
    enum class Type(val iconResource: Int) {
        ALPHABET(R.drawable.ic_sort_by_alpha),
        COLOR(R.drawable.ic_sort_by_color),
        NUMBER(R.drawable.ic_sort_by_number),
    }
}