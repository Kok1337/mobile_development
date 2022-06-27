package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.adapter.HighlightedDictionaryItem
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortType
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortTypeItem
import java.util.*

data class AreaItem(val id: UUID, val value: String, val hasSection: Boolean) :
    HighlightedDictionaryItem {
    override fun getName(): String = value
    override fun isHighlighted(): Boolean = hasSection

    companion object : SortTypeItem<AreaItem> {
        override fun getSortTypes(): Array<SortType<AreaItem>> = arrayOf(
            SortType(SortType.Type.COLOR) { o1, o2 ->
                var res = o2.hasSection.compareTo(o1.hasSection)
                if (res == 0) res =
                    try {
                        o1.value.toInt().compareTo(o2.value.toInt())
                    } catch (exception: NumberFormatException) {
                        o1.value.length.compareTo(o2.value.length)
                    }
                res
            },
            SortType(SortType.Type.NUMBER) { o1, o2 ->
                try {
                    o1.value.toInt().compareTo(o2.value.toInt())
                } catch (exception: NumberFormatException) {
                    o1.value.length.compareTo(o2.value.length)
                }
            },
        )
    }
}