package com.kok1337.mobiledev.presentation.view.highlighted

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.kok1337.mobiledev.R

class HighlightedRelativeLayout : RelativeLayout {
    private var highlighted = false
        set(value) {
            if (field != value) {
                field = value
                refreshDrawableState()
            }
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    @SuppressLint("CustomViewStyleable")
    private fun initAttrs(attrS: AttributeSet?) {
        if (attrS == null) return

        val attributeArray: TypedArray = context.obtainStyledAttributes(attrS, R.styleable.HighlighterState)
        highlighted = attributeArray.getBoolean(R.styleable.HighlighterState_state_item_highlighted, false)
        attributeArray.recycle()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        if (highlighted) {
            val drawableState = super.onCreateDrawableState(extraSpace + 1)
            mergeDrawableStates(drawableState, STATE_ITEM_HIGHLIGHTED)
            return drawableState
        }
        return super.onCreateDrawableState(extraSpace)
    }

    fun setState_item_highlighted(highlighted: Boolean) { this.highlighted = highlighted }

    companion object {
        private val STATE_ITEM_HIGHLIGHTED = intArrayOf(R.attr.state_item_highlighted)
    }
}