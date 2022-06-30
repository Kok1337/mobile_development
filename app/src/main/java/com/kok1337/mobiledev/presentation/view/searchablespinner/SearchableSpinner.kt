package com.kok1337.mobiledev.presentation.view.searchablespinner

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.util.showDialog
import com.kok1337.mobiledev.presentation.util.showToast

class SearchableSpinner : AppCompatTextView {

    private val nullSelectionString = resources.getString(R.string.nullSelection)
    private val defaultTitle = resources.getString(R.string.title)
    private val nullItemPosition = -1

    private var title: String = defaultTitle
        set(value) {
            field = value.ifEmpty { defaultTitle }
        }

    private var autoSelect: Boolean = true
    private var emptyItem: Boolean = true

    var searchableSpinnerConfiguration: SearchableSpinnerConfiguration<*>? = null
        set(value) {
            field = value
            selectedItemPosition = getItemPosition(searchableSpinnerConfiguration)
        }
    private var selectedItemPosition = nullItemPosition

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
        title = attributeArray.getString(R.styleable.SearchableSpinner_title) ?: ""
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

        val searchString = when(val selectedItem = configuration?.selectedItem) {
            null -> ""
            is SpinnerItem -> selectedItem.getSearchableString()
            else -> ""
        }
        val dialog = SearchableSpinnerDialog(
            title, configuration!!.bindingAdapter, emptyItem, configuration.sortTypes, searchString
        ) { id, item -> pickItem(item, id, configuration)}

        showDialog(dialog, context)
        // dialog.show( (context as AppCompatActivity).supportFragmentManager, SearchableSpinnerDialog.TAG)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getSelectedItem(): T? = getSelectedItem(searchableSpinnerConfiguration) as T?

    private fun <T> getSelectedItem(configuration: SearchableSpinnerConfiguration<T>?): T? {
        val adapter = configuration?.bindingAdapter
        return if (adapter?.isCorrectPosition(selectedItemPosition) == true)
            adapter.getItemByPosition(selectedItemPosition)
        else null
    }

    fun tryMakeAutoSelect() = tryMakeAutoSelect(searchableSpinnerConfiguration)

    private fun <T> tryMakeAutoSelect(configuration: SearchableSpinnerConfiguration<T>?) {
        val bindingAdapter = configuration?.bindingAdapter
        when (bindingAdapter?.itemCount) {
            0 -> pickItem(null, nullItemPosition, searchableSpinnerConfiguration)
            1 -> pickItem(bindingAdapter.getItemByPosition(0), 0, configuration)
            else -> if (configuration?.selectedItem != null) {
                selectedItemPosition = getItemPosition(searchableSpinnerConfiguration)
                pickItem(configuration.selectedItem, selectedItemPosition, configuration)
            }
        }
    }

    private fun <T> getItemPosition(configuration: SearchableSpinnerConfiguration<T>?): Int {
        val item = configuration?.selectedItem ?: return nullItemPosition
        if (!configuration.bindingAdapter.containsItem(item)) return nullItemPosition
        val position = configuration.bindingAdapter.getPositionByItem(item)
        return if (position == -1) nullItemPosition else position
    }

    private fun <T> pickItem(item: T?, position: Int, configuration: SearchableSpinnerConfiguration<T>?) {
        selectedItemPosition = position
        configuration?.itemSelectedListener?.invoke(item)
        text = when(item) {
            null -> nullSelectionString
            is SpinnerItem -> item.getSpinnerText()
            else -> item.toString()
        }
    }

    class SearchableSpinnerConfiguration<T>(
        val bindingAdapter: BindingAdapter<T, *>,
        var sortTypes: Array<SortType<T>> = emptyArray(),
        var selectedItem: T? = null,
        var itemSelectedListener: ((item: T?) -> Unit)?
    )
}