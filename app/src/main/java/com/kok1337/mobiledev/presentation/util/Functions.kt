package com.kok1337.mobiledev.presentation.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.di.AppComponent
import com.kok1337.mobiledev.presentation.adapter.recyclerbindingadapter.BindingAdapter
import com.kok1337.mobiledev.presentation.view.searchablespinner.SearchableSpinner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

fun unwrapAppCompatActivity(context: Context): AppCompatActivity {
    if (context !is Activity && context is ContextWrapper)
        return unwrapAppCompatActivity(context.baseContext)
    return context as AppCompatActivity
}

fun showDialog(dialog: DialogFragment, context: Context) {
    dialog.show(unwrapAppCompatActivity(context).supportFragmentManager, dialog::class.simpleName ?: "TAG")
}