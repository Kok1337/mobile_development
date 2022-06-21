package com.kok1337.mobiledev.presentation.view.searchablespinner

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.util.showToast

class SearchableSpinner : AppCompatTextView {

    private val nullSelectionString = resources.getString(R.string.nullSelection)
    private val defaultTitle = resources.getString(R.string.title)

    private var title: String = defaultTitle
        set(value) {
            field = value.ifEmpty { defaultTitle }
        }

    private var autoSelect: Boolean = true
    private var emptyItem: Boolean = true

    var searchableSpinnerConfiguration: SearchableSpinnerConfiguration<*>? = null
        set(value) {
            field = value
            if (autoSelect) {
                makeAutoSelect()
            }
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrS: AttributeSet?) : super(context, attrS) {
        initSearchableSpinner(attrS)
    }
    constructor(context: Context, attrS: AttributeSet?, defStyleAttr: Int) : super(context, attrS, defStyleAttr) {
        initSearchableSpinner(attrS)
    }

    private fun initSearchableSpinner(attrS: AttributeSet?) {
        initAttrs(attrS)
        initView()
    }

    private fun initAttrs(attrS: AttributeSet?) {
        if (attrS == null) return

        val attributeArray: TypedArray = context.obtainStyledAttributes(attrS, R.styleable.SearchableSpinner)
        title = attributeArray.getText(R.styleable.SearchableSpinner_title) as String
        autoSelect = attributeArray.getBoolean(R.styleable.SearchableSpinner_autoSelect, true)
        emptyItem = attributeArray.getBoolean(R.styleable.SearchableSpinner_emptyItem, true)
        attributeArray.recycle()
    }

    init {
        initView()
    }

    private fun initView() {
        maxLines = 1
        text = nullSelectionString
        setOnClickListener { openDialog(searchableSpinnerConfiguration) }
    }

    private fun <T> openDialog(configuration: SearchableSpinnerConfiguration<T>?) {
        if (searchableSpinnerConfiguration == null) {
            showToast(context, "Конфигурация отсутсвует")
            return
        }

        val dialog = SearchableSpinnerDialog(
            title, configuration!!.bindingAdapter, emptyItem, configuration.sortTypes
        ) { _, item -> pickItem(item)}

        dialog.show( (context as AppCompatActivity).supportFragmentManager, SearchableSpinnerDialog.TAG)
    }

    private fun makeAutoSelect() {
        val bindingAdapter = searchableSpinnerConfiguration?.bindingAdapter
        when (bindingAdapter?.itemCount) {
            0 -> pickItem(null)
            1 -> pickItem(bindingAdapter.getItemByPosition(0))
        }
    }

    private fun <T> pickItem(item: T?) {
        showToast(context, item.toString())
    }

    // fun

    fun resetItem() {

    }

    class SearchableSpinnerConfiguration<T>(
        val sortTypes: Array<SortType<T>>,
        val bindingAdapter: BindingAdapter<T, *>,
        val itemSelectedListener: ((item: T?, position: Int) -> Unit)?
    ) {
        private constructor(builder: Builder<T>) : this(
            builder.sortTypes,
            builder.bindingAdapter,
            builder.itemSelectedListener
        )

        class Builder<T>(val bindingAdapter: BindingAdapter<T, *>) {

            var sortTypes: Array<SortType<T>> = emptyArray()
                private set

            var itemSelectedListener: ((item: T?, position: Int) -> Unit)? = null
                private set

            fun sortTypes(sortTypes: Array<SortType<T>>) =
                apply { this.sortTypes = sortTypes }

            fun itemSelectedListener(itemSelectedListener: ((item: T?, position: Int) -> Unit)) =
                apply { this.itemSelectedListener = itemSelectedListener }

            fun build() = SearchableSpinnerConfiguration(this)
        }
    }

}