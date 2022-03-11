package com.kok1337.mobiledev.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AbsBindingAdapter<T, B : ViewDataBinding>(
    private val layoutId: Int,
    private val variableId: Int,
    onItemClickListener: OnItemClickListener<T>?,
) : RecyclerView.Adapter<AbsBindingAdapter.BindingViewHolder<B>>() {

    private var items: List<T> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClickListener: OnItemClickListener<T>? = onItemClickListener
        set(value) { field = value }

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
        holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(position, item) }
        viewSetting(binding, item, position)
    }

    abstract fun viewSetting(binding: B, item: T, position: Int)

    class BindingViewHolder<BH : ViewDataBinding>(val binding: BH) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener<T> { fun onItemClick(position: Int, item: T) }

}