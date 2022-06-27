package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.adapter.HighlightedDictionaryItem
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortType
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortTypeItem
import java.util.*

class TaxYearItem(val taxId: UUID?, val year: Int, val draft: Boolean) : HighlightedDictionaryItem {
    override fun isHighlighted(): Boolean = !draft
    override fun getName(): String = year.toString()

    companion object : SortTypeItem<TaxYearItem> {
        override fun getSortTypes(): Array<SortType<TaxYearItem>> = arrayOf(
            SortType(SortType.Type.COLOR) { o1, o2 ->
                var res = o2.draft.compareTo(o1.draft)
                if (res == 0) res = o1.year.compareTo(o2.year)
                res
            },
            SortType(SortType.Type.NUMBER) { o1, o2 ->
                o1.year.compareTo(o2.year)
            },
        )
    }
}