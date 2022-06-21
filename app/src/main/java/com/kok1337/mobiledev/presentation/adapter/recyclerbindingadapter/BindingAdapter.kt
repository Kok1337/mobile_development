package com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindingAdapter<T, B : ViewDataBinding>(
    private val layoutId: Int,
    private val variableId: Int,
    private var onItemClickListener: ((position: Int, item: T) -> Unit)? = null
) : RecyclerView.Adapter<BindingAdapter.BindingViewHolder<B>>() {

    private var items: List<T> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sort(comparator: Comparator<T>) {
        items.sortedWith(comparator)
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int) : T {
        return items[position]
    }

    fun setOnItemClickListener(listener: (position: Int, item: T) -> Unit) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<B> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutId, parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<B>, position: Int) {
        val item: T = items[position]
        val binding: B = holder.binding
        binding.setVariable(variableId, item)
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(position, item) }
        viewSetting(binding, item, position)
    }

    open fun viewSetting(binding: B, item: T, position: Int) {}

    class BindingViewHolder<BH : ViewDataBinding>(val binding: BH) :
        RecyclerView.ViewHolder(binding.root)
}