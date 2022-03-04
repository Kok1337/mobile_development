package com.kok1337.mobiledev.toolbar.vievs

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable

class ToolbarIcon : androidx.appcompat.widget.AppCompatImageView, Checkable {

    companion object {
        private val CHECKED_STATE = IntArray( android.R.attr.state_checked )
    }

    private var checked: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrS: AttributeSet?) : super(context, attrS)
    constructor(context: Context, attrS: AttributeSet?, defStyleAttr: Int) : super(context, attrS, defStyleAttr)

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) mergeDrawableStates(drawableState, CHECKED_STATE)
        return super.onCreateDrawableState(extraSpace)
    }

    override fun setChecked(flag: Boolean) { checked = flag; refreshDrawableState() }

    override fun isChecked() = checked

    override fun toggle() { isChecked = !checked }
}