package com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter

import androidx.recyclerview.widget.RecyclerView

class RecyclerConfiguration(
    private val bindingAdapter: BindingAdapter<*, *>,
    private val layoutManager: RecyclerView.LayoutManager
) {

    private constructor(builder: Builder) : this(builder.bindingAdapter!!, builder.layoutManager!!)

    fun configure(recyclerView: RecyclerView) {
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = bindingAdapter
    }

    class Builder {

        var bindingAdapter: BindingAdapter<*, *>? = null
            private set

        var layoutManager: RecyclerView.LayoutManager? = null
            private set

        fun bindingAdapter(bindingAdapter: BindingAdapter<*, *>) = apply { this.bindingAdapter = bindingAdapter }

        fun layoutManager(layoutManager: RecyclerView.LayoutManager) = apply { this.layoutManager = layoutManager }

        fun build() = RecyclerConfiguration(this)
    }
}