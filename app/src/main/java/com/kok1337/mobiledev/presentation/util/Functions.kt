package com.kok1337.mobiledev.presentation.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.databinding.adapters.RadioGroupBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.di.AppComponent
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.util.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.getAppComponent(): AppComponent = (activity?.applicationContext as App).appComponent

fun ViewModel.async(block: suspend () -> Unit) {
    viewModelScope.launch {
        withContext(Dispatchers.Default) {
            block.invoke()
        }
    }
}

fun <T> setItemsAndTryAutoSelect(searchableSpinner: SearchableSpinner, bindingAdapter: BindingAdapter<T, *>, items: List<T>) {
    bindingAdapter.setItems(items)
    searchableSpinner.tryMakeAutoSelect()
}