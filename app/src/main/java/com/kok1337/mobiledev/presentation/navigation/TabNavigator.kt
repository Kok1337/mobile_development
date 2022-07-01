package com.kok1337.mobiledev.presentation.navigation

import androidx.fragment.app.Fragment

interface TabNavigator {
    fun openStartFragment()
    fun openNotStartFragment(fragmentId: Int)
    fun openNotStartFragment(fragment: Fragment)
    fun isStartFragment(fragment: Fragment): Boolean
    fun isStartFragment(fragmentName: String?): Boolean
    fun isContainedInBackStack(fragment: Fragment): Boolean
    fun isContainedInBackStack(fragmentName: String?): Boolean
    fun removeFromBackStack(fragment: Fragment)
    fun removeFromBackStack(fragmentName: String?)
}