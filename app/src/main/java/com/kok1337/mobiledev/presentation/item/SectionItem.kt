package com.kok1337.mobiledev.presentation.item

import com.kok1337.mobiledev.presentation.view.searchablespinner.SortType
import com.kok1337.mobiledev.presentation.view.searchablespinner.SortTypeItem
import com.kok1337.mobiledev.presentation.view.searchablespinner.SpinnerItem

data class SectionItem(val name: String, val s: Double) : SpinnerItem {
    override fun getSpinnerText(): String = name
    override fun getSearchableString(): String = "$name $s"
    val sStr: String = s.toString()

    companion object : SortTypeItem<SectionItem> {
        override fun getSortTypes(): Array<SortType<SectionItem>> = arrayOf(
            SortType(SortType.Type.NUMBER) { o1, o2 ->
                try { o1.name.toInt().compareTo(o2.name.toInt()) }
                catch (exception: NumberFormatException) { o1.name.length.compareTo(o2.name.length) }
            },
            SortType(SortType.Type.SQUARE) { o1, o2 ->
                o1.s.compareTo(o2.s)
            }
        )
    }
}