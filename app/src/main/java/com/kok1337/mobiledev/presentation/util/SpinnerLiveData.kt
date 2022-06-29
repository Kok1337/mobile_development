package com.kok1337.mobiledev.presentation.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SpinnerLiveData<T> {

    private val _itemCountLD = MutableLiveData(0)
    val itemCountLD: LiveData<Int> = _itemCountLD
    private val _selectedItemLD = MutableLiveData<T?>(null)
    val selectedItemLD: LiveData<T?> = _selectedItemLD
    private val _itemsLD = MutableLiveData<List<T>>(emptyList())
    val itemsLD: LiveData<List<T>> = _itemsLD

    val selectedItem: T?
        get() = selectedItemLD.value

    fun trySetNewItem(item: T?): Boolean {
        if (selectedItem != item) {
            setSelectedItem(item)
            return true
        }
        return false
    }

    fun tryPostNewItem(item: T?): Boolean {
        if (selectedItem != item) {
            postSelectedItem(item)
            return true
        }
        return false
    }

    fun setItems(items: List<T>) {
        _itemsLD.value = items
        _itemCountLD.value = items.size
    }

    fun setSelectedItem(item: T?) {
        _selectedItemLD.value = item
    }

    fun setEmptyList() {
        _itemsLD.value = emptyList()
        _selectedItemLD.value = null
        _itemCountLD.value = 0
    }

    fun postItems(items: List<T>) {
        _itemsLD.postValue(items)
        _itemCountLD.postValue(items.size)
    }

    fun postSelectedItem(item: T?) {
        _selectedItemLD.postValue(item)
    }

    fun postEmptyList() {
        _itemsLD.postValue(emptyList())
        _selectedItemLD.postValue(null)
        _itemCountLD.postValue(0)
    }

}