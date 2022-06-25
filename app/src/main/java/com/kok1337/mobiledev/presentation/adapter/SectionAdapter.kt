package com.kok1337.mobiledev.presentation.adapter

import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.ItemDictionaryBinding
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.item.SectionItem

class SectionAdapter : BindingAdapter<SectionItem, ItemDictionaryBinding>(R.layout.item_section, BR.section)