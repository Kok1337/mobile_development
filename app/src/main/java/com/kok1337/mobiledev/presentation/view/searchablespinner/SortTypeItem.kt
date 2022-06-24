package com.kok1337.mobiledev.presentation.view.searchablespinner

interface SortTypeItem<T> {
    fun getSortTypes(): Array<SortType<T>>
}