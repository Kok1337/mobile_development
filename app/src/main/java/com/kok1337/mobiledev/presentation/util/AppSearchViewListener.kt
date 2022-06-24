package com.kok1337.mobiledev.presentation.util

import android.widget.SearchView

class AppSearchViewListener(val onSuccess: (String?) -> Unit) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(p0: String?): Boolean = true
    override fun onQueryTextChange(s: String?): Boolean {
        onSuccess(s)
        return true
    }
}