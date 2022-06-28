package com.kok1337.mobiledev.presentation.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.SearchView

class AppSearchViewListener(val onSuccess: (String?) -> Unit) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean = true
    override fun onQueryTextChange(s: String?): Boolean {
        onSuccess(s)
        return true
    }
}

class AppTextWatcher(val onSuccess: (String?) -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onSuccess(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {}
}