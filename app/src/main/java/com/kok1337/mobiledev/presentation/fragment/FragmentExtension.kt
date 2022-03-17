package com.kok1337.mobiledev.presentation.fragment

import androidx.fragment.app.Fragment
import com.kok1337.mobiledev.app.App
import com.kok1337.mobiledev.di.AppComponent

fun Fragment.getAppComponent(): AppComponent = (activity?.applicationContext as App).appComponent