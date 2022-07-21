package com.kok1337.mobiledev.presentation.navigation

import androidx.fragment.app.Fragment

interface ToolbarNavigator {

    fun showMapScreen()

    fun showCameraScreen()

    fun showWorkTypesScreen()

    fun showSynchronizationScreen()

    fun showSettingScreen()

    fun launchFragment(fragment: Fragment)

    fun goBack()

}