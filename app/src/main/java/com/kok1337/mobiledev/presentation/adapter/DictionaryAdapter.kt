package com.kok1337.mobiledev.presentation.adapter

import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.ItemDictionaryBinding
import com.kok1337.mobiledev.presentation.util.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.item.DictionaryItem

open class DictionaryAdapter<T> : BindingAdapter<T, ItemDictionaryBinding>(
    R.layout.item_dictionary, BR.dictionary
) where T : DictionaryItem