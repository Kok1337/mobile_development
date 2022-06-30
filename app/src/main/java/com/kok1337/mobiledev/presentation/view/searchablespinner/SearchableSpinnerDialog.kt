package com.kok1337.mobiledev.presentation.view.searchablespinner

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.databinding.DialogSearchablespinnerBinding
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.RecyclerConfiguration
import com.kok1337.mobiledev.presentation.util.AppSearchViewListener

class SearchableSpinnerDialog<T>(
    private val title: String,
    private val bindingAdapter: BindingAdapter<T, *>,
    private val nullSelection: Boolean = true,
    private val sortTypes: Array<SortType<T>>,
    private val searchString: String? = null,
    private var onItemSelectedListener: (position: Int, item: T?) -> Unit
) : DialogFragment(R.layout.dialog_searchablespinner) {

    companion object {
        const val TAG: String = "SearchableSpinnerDialog"
    }

    private var currentSortType: Int = 0

    private var _binding: DialogSearchablespinnerBinding? = null
    private val binding get() = _binding!!

    private val searchViewListener = AppSearchViewListener {
        val query = it ?: ""
        bindingAdapter.filter { item ->
            item is SpinnerItem && item.getSearchableString().contains(query, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogSearchablespinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerConfiguration()
        setupViews()
    }

    private fun setupRecyclerConfiguration() {
        bindingAdapter.setOnItemClickListener { position, item -> selectItem(position, item) }
        RecyclerConfiguration.Builder()
            .bindingAdapter(bindingAdapter)
            .layoutManager(LinearLayoutManager(context))
            .build().configure(binding.listView)
    }

    private fun setupViews() {

        if (searchString != null && searchString.isNotEmpty()) {
            binding.searchView.setQuery(searchString, true)
            bindingAdapter.filter { item ->
                item is SpinnerItem && item.getSearchableString() == searchString
            }
        }

        binding.titleTextView.text = title
        binding.nullSelection.visibility = if (nullSelection) View.VISIBLE else View.GONE
        binding.nullSelection.setOnClickListener { selectItem(-1, null) }
        binding.sortIcon.visibility = if (sortTypes.isEmpty()) View.GONE else View.VISIBLE
        binding.searchView.setOnQueryTextListener(searchViewListener)

        if (sortTypes.isNotEmpty()) {
            setupSortType()
            binding.sortIcon.setOnClickListener {
                currentSortType = (currentSortType + 1) % sortTypes.size
                setupSortType()
            }
        }
    }

    private fun setupSortType() {
        Log.e("DIALOG", currentSortType.toString())
        val sortType = sortTypes[currentSortType]
        binding.sortIcon.setImageResource(sortType.iconResource)
        bindingAdapter.sort(sortType.comparator)
    }

    private fun selectItem(position: Int, item: T?) {
        onItemSelectedListener.invoke(position, item)
        dismiss()
    }

    override fun dismiss() {
        resetSearchView()
        super.dismiss()
    }

    private fun searchViewIsEmpty(): Boolean {
        return binding.searchView.query.isEmpty()
    }

    private fun resetSearchView() {
        binding.searchView.setQuery("", true)
    }

    override fun onResume() {
        super.onResume()
        dialog?.setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action != KeyEvent.ACTION_DOWN) {
                if (!searchViewIsEmpty()) {
                    resetSearchView()
                    return@OnKeyListener true
                }
                return@OnKeyListener false
            } else return@OnKeyListener false
        })
    }
}