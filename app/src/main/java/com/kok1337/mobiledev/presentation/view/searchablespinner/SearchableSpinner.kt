package com.kok1337.mobiledev.presentation.view.searchablespinner

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.presentation.util.recyclerbindingadapter.BindingAdapter
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
            selectedItemPosition = nullItemPosition
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
        ) { id, item -> pickItem(item, id, configuration)}

        dialog.show( (context as AppCompatActivity).supportFragmentManager, SearchableSpinnerDialog.TAG)
    }

    fun getSelectedItem() = getSelectedItem(searchableSpinnerConfiguration)

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
        }
    }

    private fun <T> pickItem(item: T?, id: Int, configuration: SearchableSpinnerConfiguration<T>?) {
        selectedItemPosition = id
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
        var itemSelectedListener: ((item: T?) -> Unit)?
    )
}