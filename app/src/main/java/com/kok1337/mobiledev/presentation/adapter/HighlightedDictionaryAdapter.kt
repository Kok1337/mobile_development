package com.kok1337.mobiledev.presentation.adapter

import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.ItemDictionaryBinding
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter

open class HighlightedDictionaryAdapter<T> : BindingAdapter<T, ItemDictionaryBinding>(
    R.layout.item_highlighted_dictionary, BR.highlightedDictionary
) where T : HighlightedDictionaryItem