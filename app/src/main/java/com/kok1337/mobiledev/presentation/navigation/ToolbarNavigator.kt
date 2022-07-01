package com.kok1337.mobiledev.presentation.navigation

import androidx.fragment.app.Fragment

interface ToolbarNavigator {

    fun showMapScreen()

    fun showCameraScreen()

    fun showWorkTypesScreen()

    fun showSynchronizationScreen()

    fun showSettingScreen()

    fun openTabFragment(tabFragment: Fragment)

    fun goBack()

}