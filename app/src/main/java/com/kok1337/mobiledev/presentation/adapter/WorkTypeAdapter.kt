package com.kok1337.mobiledev.presentation.adapter

import com.kok1337.mobiledev.BR
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.ItemWorkTypeBinding
import com.kok1337.mobiledev.presentation.util.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.item.WorkTypeItem

class WorkTypeAdapter(
    onItemClickListener: ((position: Int, item: WorkTypeItem) -> Unit)? = null
) : BindingAdapter<WorkTypeItem, ItemWorkTypeBinding>(
    R.layout.item_work_type, BR.workType, onItemClickListener
)
